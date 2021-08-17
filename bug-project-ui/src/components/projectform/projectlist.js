import React, { useEffect, useState } from "react";
import axios from "axios";
import Cell from "../row/cell";
import { Modal } from "react-bootstrap";
import Updateproject from "./update-project";
export default function Projectlist() {
  const [projects, setprojects] = useState({
    projectList: [],
    projectFilter: "",
    currentPage: 0,
    projectsPerPage: 5,
    totalPages: 2,
  });
  const [sortedField, setSortedField] = useState(null);
  const [projectPassed, setProjectPassed] = useState({});
  const [show, setshow] = useState(false);
  const [total, setTotal] = useState(0);

  const handleClose = () => {
    setshow(false);
  };
  useEffect(() => {
    axios.get(process.env.REACT_APP_SERVER_URL_PROJECT + "/all").then((res) => {
      setTotal(res.data.length);
    });
  }, []);
  useEffect(() => {
    axios
      .get(
        "http://localhost:8086/api/project?page=" +
          projects.currentPage +
          "&size=" +
          projects.projectsPerPage
      )
      .then((res) => {
        console.log("hello " + res.data.content);
        setprojects({
          ...projects,
          projectList: res.data.content,
          totalPages: res.data.totalPages,
          currentPage: res.data.number + 1,
        });
      });
  }, []);

  useEffect(() => {
    console.log(projects.projectFilter, "- Filter Has changed");
    let url =
      projects.projectFilter == ""
        ? process.env.REACT_APP_SERVER_URL_PROJECT_PAGE
        : process.env.REACT_APP_SERVER_URL_PROJECT_FILTER +
          projects.projectFilter;
    axios.get(url).then((res) => {
      setprojects({ ...projects, projectList: res.data });
      console.log(projects);
    });
  }, [projects.projectFilter]);
  const handleUpdate = (projectPassed) => {
    setProjectPassed(projectPassed);
    setshow(true);
  };
  const setProjectsPerPage = () => {
    projects.currentPage = 0;

    axios
      .get(
        "http://localhost:8086/api/project?page=" +
          projects.currentPage +
          "&size=" +
          projects.projectsPerPage
      )
      .then((res) => {
        setprojects({
          ...projects,
          projectList: res.data.content,
          totalPages: res.data.totalPages,
          currentPage: res.data.number + 1,
        });
      });
  };
  const toNextPage = () => {
    axios
      .get(
        "http://localhost:8086/api/project?page=" +
          projects.currentPage +
          "&size=" +
          projects.projectsPerPage
      )
      .then((res) => {
        setprojects({
          ...projects,
          projectList: res.data.content,
          totalPages: res.data.totalPages,
          currentPage: res.data.number + 1,
        });
      });
  };
  const toPrevPage = () => {
    projects.currentPage -= 2;
    axios
      .get(
        "http://localhost:8086/api/project?page=" +
          projects.currentPage +
          "&size=" +
          projects.projectsPerPage
      )
      .then((res) => {
        setprojects({
          ...projects,
          projectList: res.data.content,
          totalPages: res.data.totalPages,
          currentPage: res.data.number + 1,
        });
      });
  };

  const eachitem = (item) => {
    return (
      <tr>
        <td>
          <a style={{ textDecoration: "none" }} href={"/getProject/" + item.id}>
            {item.name}
          </a>
        </td>
        <Cell content={item.description}></Cell>
        <td>{item.createdDate}</td>
        <td>
          <button onClick={() => handleUpdate(item)}>Update Project</button>
        </td>
      </tr>
    );
  };

  const handlerChange = (event) => {
    setprojects({ ...projects, [event.target.name]: event.target.value });
  };

  if (sortedField !== null) {
    projects.projectList.sort((a, b) => {
      if (a[sortedField] < b[sortedField]) {
        return -1;
      }
      if (a[sortedField] > b[sortedField]) {
        return 1;
      }
      return 0;
    });
  }

  return (
    <div>
      <h1>Project list</h1>
      <h2>Total Projects : {total}</h2>
      Filter:
      <input
        value={projects.projectFilter}
        onChange={handlerChange}
        name="projectFilter"
      ></input>
      <table class="table table-striped table-sm">
        <thead class="thead-dark" color="Tomato">
          <tr>
            <th scope="col" onClick={() => setSortedField("name")}>
              Name
            </th>
            <th scope="col" onClick={() => setSortedField("description")}>
              Description
            </th>
            <th scope="col" onClick={() => setSortedField("createdDate")}>
              Created Date
            </th>
            <th scope="col">Action</th>
          </tr>
        </thead>
        <tbody>{projects.projectList.map((item) => eachitem(item))}</tbody>
      </table>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header>
          <Modal.Title>Update Project</Modal.Title>
          <button variant="secondary" onClick={handleClose}>
            Close
          </button>
        </Modal.Header>
        <Modal.Body>
          <Updateproject project={projectPassed}></Updateproject>
        </Modal.Body>
      </Modal>
      <div className="row">
        <div className="col">
          <input
            value={projects.projectsPerPage}
            onChange={handlerChange}
            name="projectsPerPage"
          />
          <button onClick={setProjectsPerPage} class="btn btn-primary">
            Set
          </button>
        </div>
        <div className="col">
          <h4>
            Showing page {projects.currentPage} of {projects.totalPages}
          </h4>
        </div>
        <div className="col">
          <nav aria-label="Page navigation example">
            <ul class="pagination">
              <button
                onClick={toPrevPage}
                disabled={projects.currentPage === 1}
              >
                prev
              </button>
              <button class="btn btn-primary">{projects.currentPage}</button>
              <button
                onClick={toNextPage}
                disabled={projects.currentPage === projects.totalPages}
              >
                next
              </button>
            </ul>
          </nav>
        </div>
      </div>
    </div>
  );
}
