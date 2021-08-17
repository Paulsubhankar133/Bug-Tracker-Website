import axios from "axios";
import React, { useState } from "react";
import { useEffect } from "react";
export default function UpdateBug(props) {
  const [selectedBug, setselectedBug] = useState(props.bug);
  // const [bugList, setBugList] = useState([]);
  // const [selectedBug,setSelectedBug] = useState({id:"",name:" ",description:" ",createdDate:"",completedDate:"",ownerName:"",projectId:"",status:"",priority:""})
  useEffect(() => {
    if (!selectedBug) {
      setselectedBug(selectedBug);
    }
  }, []);
  // useEffect(() => {
  //     axios.get(process.env.REACT_APP_SERVER_URL_BUG)
  //         .then(response => {
  //             setBugList(response.data );
  //         });
  // }, [selectedBug]);
  const update = () => {
    const promise = axios.put(
      process.env.REACT_APP_SERVER_URL_BUG_UPDATE + selectedBug.id,
      selectedBug
    );
    promise.then((response) => {
      console.log(response);
    });
    promise.catch((error) => {
      console.log(error);
    });
    console.log("updated");
  };
  // const handleBugSelection = (event) => {
  //     const selectedBug = bugList.find((bug) => bug.id === event.target.value);
  //     setSelectedBug(selectedBug);
  // }
  const handleChangeForselectedBug = (event) => {
    const updatedBugSpecific = {
      ...selectedBug,
      [event.target.name]: event.target.value,
    };
    setselectedBug(updatedBugSpecific);
  };
  return (
    // <div>
    //     <h3>Update Bug</h3>
    //     <form>
    //         <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example"  onChange={handleBugSelection} name='id'>
    //             <option selected> Open this bug</option>
    //             {
    //                bugList.map(bug => {
    //                     return (
    //                         <option value={bug.id}>{bug.name}</option>

    //                     )
    //                 })
    //             }

    //         </select>

    //         <div class="row">
    //             <div class="col">
    //                 <input class="form-control" value={selectedBug.name} onChange={handleChangeForSelectedBug} name='name' disabled></input>
    //             </div>
    //             <div class="col">
    //                 <input class="form-control" value={selectedBug.ownerName} onChange={handleChangeForSelectedBug} name='ownerName' disabled></input>
    //             </div>
    //             <div class="col">
    //                 <input class="form-control" value={selectedBug.description} onChange={handleChangeForSelectedBug} name='description'></input>
    //             </div>
    //             <div class="col">
    //                 <input class="form-control" value={selectedBug.projectId} onChange={handleChangeForSelectedBug} name='projectId' disabled></input>
    //             </div>
    //         </div>
    //     </form>

    //     <button onClick={update} class="btn btn-primary">Update</button>
    // </div>
    <div>
      {/* <h3>Update Project </h3> */}
      <form>
        <div class="row mt-2">
          <input
            className="form-control"
            value={selectedBug.name}
            onChange={handleChangeForselectedBug}
            name="name"
            disabled
          ></input>
        </div>
        <div class="row mt-2 mb-2">
          <textarea
            className="form-control"
            value={selectedBug.ownerName}
            onChange={handleChangeForselectedBug}
            name="description"
          ></textarea>
        </div>
        <div class="row mt-2 mb-2">
          <textarea
            className="form-control"
            value={selectedBug.ownerEmail}
            onChange={handleChangeForselectedBug}
            name="ownerEmail"
          ></textarea>
        </div>
        <div class="row mt-2 mb-2">
          <textarea
            className="form-control"
            value={selectedBug.description}
            onChange={handleChangeForselectedBug}
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
