import { Switch, Route, Link, BrowserRouter } from "react-router-dom";
import { Nav, Navbar, Container } from "react-bootstrap";
import Projectform from "./projectform/projectform";
import Projectlist from "./projectform/projectlist";
import Bugform from "./bugform/bugform";
import React from "react";
import ProjectInfo from "./projectform/projectlistinfo";
import logo from "../logo.png";
import Buglist from "./bugform/buglist";

export default function HomePage() {
  return (
    <div>
      <BrowserRouter>
        <Navbar
          collapseOnSelect
          bg="dark"
          expand="lg"
          variant="dark"
          sticky="top"
        >
          <Container>
            <Navbar.Brand href="#">
              <img
                src={logo}
                width="40"
                height="40"
                className="img-circle"
                alt=""
              />
            </Navbar.Brand>
            <Nav.Link href="/">
              <Navbar.Brand>Home</Navbar.Brand>
            </Nav.Link>
            <Navbar.Toggle aria-controls="responsive-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
              <Nav className="me-auto">
                <Nav.Link href="/bugList">Bug List</Nav.Link>
                <Nav.Link href="/createProject">Create Project</Nav.Link>
                <Nav.Link href="/createBug">CreateBug</Nav.Link>
              </Nav>
            </Navbar.Collapse>
          </Container>
        </Navbar>

        <Switch>
          <Route exact path="/">
            <Projectlist></Projectlist>
          </Route>

          <Route path="/createProject">
            <Projectform></Projectform>
          </Route>
          <Route path="/createBug">
            <Bugform></Bugform>
          </Route>
          <Route path="/bugList">
            <Buglist></Buglist>
          </Route>
          <Route path="/getProject/:projectid">
            <ProjectInfo></ProjectInfo>
          </Route>
        </Switch>
      </BrowserRouter>
    </div>
  );
}
