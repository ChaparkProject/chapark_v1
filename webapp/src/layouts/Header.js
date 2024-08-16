
import { Container, Navbar, Nav, NavDropdown } from 'react-bootstrap';
import React, { forwardRef } from 'react';
import { Link } from 'react-router-dom';

const Header = forwardRef((props, ref) => {
    return (
        <Navbar expand="lg" className="bg-body-tertiary fixed-top" id='header' ref={ref}>
          <Container>
            <Navbar.Brand href='/'>Chapark</Navbar.Brand>

            <Navbar.Toggle/>

            <Navbar.Collapse >
              <Nav className="ms-auto">

                <NavDropdown title="게시판" id="basic-nav-dropdown">
                  <NavDropdown.Item href="#action/3.1">충청도 게시판</NavDropdown.Item>
                  <NavDropdown.Item href="#action/3.2">전라도 게시판</NavDropdown.Item>
                  <NavDropdown.Item href="#action/3.2">경상도 게시판</NavDropdown.Item>
                  <NavDropdown.Item href="#action/3.2">경기도 게시판</NavDropdown.Item>
                </NavDropdown>
                <Link to="/Login">로그인/회원가입</Link>
              </Nav>
            </Navbar.Collapse>
          </Container>
        </Navbar>
    )
  });
export default Header;