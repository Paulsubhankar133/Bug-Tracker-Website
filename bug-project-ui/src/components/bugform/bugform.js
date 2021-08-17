import axios from "axios";
import React, { useState, useEffect } from "react";
import { useAlert } from "react-alert";
import {
  Form,
  Row,
  Col,
  InputGroup,
  DropdownButton,
  FormControl,
} from "react-bootstrap";
export default function Bugform() {
  const [bug, setBug] = useState({
    name: "",
    description: "",
    ownerName: "",
    ownerEmail: "",
    projectId: "",
  });
  const [project, setProject] = useState([]);

  useEffect(() => {
    if (!project.length) {
      const promise = axios.get(
        process.env.REACT_APP_SERVER_URL_PROJECT + "/all"
      );
      promise
        .then((response) => response.data)
        .then((projectReturned) => {
          setProject(projectReturned);
        });
    }
  });

  const alert = useAlert();

  const validateName = (content) => {
    if (content.length < 3 || content.length > 25) {
      return false;
    }
    return true;
  };

  const validateDescription = (content) => {
    if (content.length < 10 || content.length > 250) {
      return false;
    }
    return true;
  };

  const validateEmail = (email) => {
    const re =
      /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
  };

  const validations = () => {
    if (!validateName(bug.name)) {
      alert.show("Name must be between 3 and 25 characters");
    } else if (!validateName(bug.ownerName)) {
      alert.show("Owner name must be between 3 and 25 characters");
    } else if (!validateDescription(bug.description)) {
      alert.show("Description must be between 10 and 250 characters");
    } else if (!validateEmail(bug.ownerEmail)) {
      alert.show("Invalid email");
    } else {
      save();
    }
  };

  const save = () => {
    const promise = axios.post(process.env.REACT_APP_SERVER_URL_BUG, bug);
    promise.then((response) => {
      console.log(response);
      alert.show("Project added");
      setBug({
        name: "",
        description: "",
        ownerName: "",
        priority: "LOW",
        projectId: "",
        status: "NEW",
        email: "",
      });
    });
    promise.catch((error) => {
      console.log(error);
    });
    console.log("saved");
  };
  const handleChange = (event) => {
    const updatedBug = { ...bug, [event.target.name]: event.target.value };
    console.log(updatedBug);
    setBug(updatedBug); // 2 Way data binding
  };
  return (
    <div>
      <h3>Bug Form</h3>
      <form>
        <div class="form-row">
          <div class="form-group col-md-4">
            <select
              class="form-select form-select-lg mb-3"
              aria-label=".form-select-lg example"
              value={bug.projectId}
              onChange={handleChange}
              name="projectId"
            >
              <option selected> Open this project</option>
              {project.map((projects) => {
                return <option value={projects.id}>{projects.name}</option>;
              })}
            </select>
          </div>
          <div class="form-group col-md-4">
            <label>Name</label>
            <input
              class="form-control"
              value={bug.name}
              onChange={handleChange}
              name="name"
            ></input>
          </div>
          <div class="form-group col-md-4">
            <label>OwnerName</label>
            <input
              class="form-control"
              value={bug.ownerName}
              onChange={handleChange}
              name="ownerName"
            ></input>
          </div>
        </div>
        <div class="form-group col-md-4">
          <label>OwnerEmail</label>
          <input
            class="form-control"
            value={bug.ownerEmail}
            onChange={handleChange}
            name="ownerEmail"
          ></input>
        </div>

        <div class="form-group col-md-4">
          <label>Description</label>
          <input
            class="form-control"
            value={bug.description}
            onChange={handleChange}
            name="description"
          ></input>
        </div>
      </form>

      <button onClick={validations} class="btn btn-primary">
        Save
      </button>
    </div>
  );
}
