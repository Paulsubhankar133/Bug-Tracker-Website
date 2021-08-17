import React, { useEffect, useState } from "react";
import axios from "axios";
import UpdateBug from "./update-bug";
import { Modal } from "react-bootstrap";
export default function Buglist() {
  const [bugs, setbugs] = useState({ bugList: [], bugFilter: "" });
  const [sortedField, setSortedField] = useState(null);
  const [show, setshow] = useState(false);
  const [bugPassed, setBugPassed] = useState({});
  const handleClose = () => {
    setshow(false);
  };
  useEffect(() => {
    axios.get(process.env.REACT_APP_SERVER_URL_BUG).then((res) => {
      setbugs({ ...bugs, bugList: res.data });
    });
  }, []);

  useEffect(() => {
    console.log(bugs.bugFilter, "- Filter Has changed");
    let url =
      bugs.bugFilter == ""
        ? process.env.REACT_APP_SERVER_URL_BUG
        : process.env.REACT_APP_SERVER_URL_BUG_FILTER + bugs.bugFilter;
    axios.get(url).then((res) => {
      setbugs({ ...bugs, bugList: res.data });
      console.log(bugs);
    });
  }, [bugs.bugFilter]);

  const handlerChange = (event) => {
    setbugs({ ...bugs, [event.target.name]: event.target.value });
  };
  const handleUpdate = (bugPassed) => {
    setBugPassed(bugPassed);
    setshow(true);
  };
  if (sortedField !== null) {
    // FOR SORTING
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

  const eachitem = (item) => {
    return (
      <tr>
        <td>{item.name}</td>
        <td>{item.ownerName}</td>
        <td>{item.description}</td>
        <td>{item.createdDate}</td>
        <td>
          <button onClick={() => handleUpdate(item)}>Update Bug</button>
        </td>
      </tr>
    );
  };

  return (
    <div>
      <h1>Bug list</h1>
      Filter:
      <input
        value={bugs.bugFilter}
        onChange={handlerChange}
        name="bugFilter"
      ></input>
      <table class="table table-striped table-sm">
        <thead class="thead-dark" color="Tomato">
          <tr>
            <th scope="col" onClick={() => setSortedField("name")}>
              Name
            </th>
            <th scope="col" onClick={() => setSortedField("ownerName")}>
              ownerName
            </th>
            <th scope="col" onClick={() => setSortedField("description")}>
              Description
            </th>
            <th scope="col" onClick={() => setSortedField("createdDate")}>
              Created Date
            </th>
          </tr>
        </thead>
        <tbody>{bugs.bugList.map((item) => eachitem(item))}</tbody>
      </table>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header>
          <Modal.Title>Update Bug</Modal.Title>
          <button variant="secondary" onClick={handleClose}>
            Close
          </button>
        </Modal.Header>
        <Modal.Body>
          <UpdateBug bug={bugPassed}></UpdateBug>
        </Modal.Body>
      </Modal>
    </div>
  );
}
