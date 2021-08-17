import axios from "axios";
import React, { useState } from "react";
import { useEffect } from "react";
export default function Updateproject(props) {
  const [selectedProject, setselectedProject] = useState(props.project);
  useEffect(() => {
    if (!selectedProject) {
      setselectedProject(selectedProject);
    }
  }, []);
  const update = () => {
    const promise = axios.put(
      process.env.REACT_APP_SERVER_URL_PROJECT_UPDATE + selectedProject.id,
      selectedProject
    );
    promise.then((response) => {
      console.log(response);
    });
    promise.catch((error) => {
      console.log(error);
    });
    console.log("updated");
  };

  const handleChangeForselectedProject = (event) => {
    const updatedProjectSpecific = {
      ...selectedProject,
      [event.target.name]: event.target.value,
    };
    setselectedProject(updatedProjectSpecific);
  };
  return (
    <div>
      {/* <h3>Update Project </h3> */}
      <form>
        <div class="row mt-2">
          <input
            className="form-control"
            value={selectedProject.name}
            onChange={handleChangeForselectedProject}
            name="name"
            disabled
          ></input>
        </div>
        <div class="row mt-2 mb-2">
          <textarea
            className="form-control"
            value={selectedProject.description}
            onChange={handleChangeForselectedProject}
            name="description"
          ></textarea>
        </div>
      </form>
      <div class="text-center">
        <button onClick={update} class="btn btn-primary">
          Update
        </button>
      </div>
    </div>
  );
}
