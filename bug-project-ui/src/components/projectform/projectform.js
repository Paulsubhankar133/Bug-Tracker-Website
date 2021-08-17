import axios from "axios";
import { useAlert } from "react-alert";
import {
  Form,
  Row,
  Col,
  InputGroup,
  DropdownButton,
  FormControl,
} from "react-bootstrap";
import React, { useState } from "react";
export default function Projectform() {
  const [project, setProject] = useState({
    name: "Citi Project",
    description: "good project",
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

  const validations = () => {
    if (!validateName(project.name)) {
      alert.show("Name must be between 3 and 25 characters");
    } else if (!validateDescription(project.description)) {
      alert.show("Description must be between 10 and 250 characters");
    } else {
      save();
    }
  };

  const save = () => {
    const promise = axios.post(
      process.env.REACT_APP_SERVER_URL_PROJECT,
      project
    );
    promise.then((response) => {
      alert.show("Project Added!");
      console.log(response);
      setProject({ name: "", description: "" });
    });
    promise.catch((error) => {
      console.log(error);
    });
    console.log("saved");
  };
  const handleChange = (event) => {
    const updatedProject = {
      ...project,
      [event.target.name]: event.target.value,
    };
    console.log(updatedProject);
    setProject(updatedProject); // 2 Way data binding
  };
  return (
    <div>
      <h3 className="text-center">Project Form</h3>
      <form>
        <div class="row">
          <input
            class="form-control"
            value={project.name}
            onChange={handleChange}
            name="name"
          ></input>
        </div>
        <div class="row mt-2">
          <div class="row">
            <input
              class="form-control"
              value={project.description}
              onChange={handleChange}
              name="description"
            ></input>
          </div>
        </div>
      </form>
      <div class="col mt-2 text-center">
        <button onClick={validations} class="btn btn-primary">
          Save
        </button>
      </div>
    </div>
  );
}
