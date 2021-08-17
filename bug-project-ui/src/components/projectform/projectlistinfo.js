import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

export default function ProjectInfo() {
  // console.log("in projctInfo");
  const { projectid } = useParams();

  const [sortedField, setsortedField] = useState(null);
  // const [bugs, setBugs] = useState({ bugList: [], bugFilter: "" });
  const [bugs, setBugs] = useState({
    bugList: [],
    bugFilter: "",
    currentPage: 0,
    bugsPerPage: 2,
    totalPages: 2,
  });
  const [projectInfo, setProjectInfo] = useState({ project: {} });

  useEffect(() => {
    axios
      .get(process.env.REACT_APP_BACKEND_URL_BUG_LIST_PROJECT + projectid)
      .then((res) => {
        console.log("hello " + res.data.content);
        setBugs({
          ...bugs,
          bugList: res.data.content,
          totalPages: res.data.totalPages,
          currentPage: res.data.number + 1,
        });
      });
  }, []);

  useEffect(() => {
    const fetchProject = async () => {
      const promiseProject = await axios.get(
        process.env.REACT_APP_BACKEND_URL_PARTICULAR_PROJECT + projectid
      );
      setProjectInfo({ project: promiseProject.data });
    };
    fetchProject();
  }, []);

  useEffect(() => {
    console.log(bugs.bugFilter, "- Filter Has changed");
    console.log(process.env.REACT_APP_SERVER_URL_PROJECT_FILTER);
    let url =
      bugs.bugFilter == ""
        ? process.env.REACT_APP_BACKEND_URL_BUG_LIST_PROJECT + projectid
        : process.env.REACT_APP_BACKEND_URL_BUG_LIST_OF_PROJECT_FILTER +
          projectid +
          "/" +
          bugs.bugFilter;
    axios.get(url).then((res) => {
      setBugs({ ...bugs, bugList: res.data });
      console.log(bugs);
    });
  }, [bugs.bugFilter]);

  const handleChange = (event) => {
    setBugs({ ...bugs, [event.target.name]: [event.target.value] });
  };
  if (sortedField !== null) {
    bugs.bugList.sort((a, b) => {
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
      <div>
        <h3>{projectInfo.project.name}</h3>
        <h5>Created On {projectInfo.project.createdDate}</h5>
        <h6>{projectInfo.project.description}</h6>
      </div>

      <div>
        <h1 style={{ textAlign: "left", float: "left" }}>Bug list</h1>
        <input
          style={{
            marginTop: "5px",
            marginLeft: "10px",
            textAlign: "right",
            float: "right",
          }}
          value={bugs.bugFilter}
          onChange={handleChange}
          name="bugFilter"
        ></input>
        <label style={{ marginTop: "7px", textAlign: "right", float: "right" }}>
          <h6>Search Bug :</h6>
        </label>
        <div></div>
        <table class="table table-striped ">
          <thead>
            <tr>
              <th scope="col" onClick={() => setsortedField("name")}>
                {" "}
                Name{" "}
              </th>
              <th scope="col" onClick={() => setsortedField("ownerName")}>
                {" "}
                OwnerName
              </th>
              <th
                scope="col"
                onClick={() => {
                  setsortedField("email");
                }}
              >
                {" "}
                Email
              </th>
              <th scope="col" onClick={() => setsortedField("description")}>
                {" "}
                description{" "}
              </th>
            </tr>
          </thead>
          <tbody>
            {bugs.bugList.map((bug) => {
              console.log(bug);
              return (
                <tr scope="row">
                  <td>{bug.name}</td>
                  <td>{bug.ownerName}</td>
                  <td>{bug.email}</td>
                  <td>{bug.description}</td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    </div>
  );
}
