import { useRef, useState, useEffect } from "react";
import {
  faCheck,
  faTimes,
  faInfoCircle,
  faSolid,
  faChevronLeft,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import "../../styles/RegisterPage.css";
import {
  IconButton,
  Button,
  OutlinedInput,
  InputLabel,
  FormControl,
  TextField,
  InputAdornment,
  FormHelperText,
} from "@mui/material";
import { Visibility, VisibilityOff } from "@mui/icons-material";
import { ReactComponent as BackIcon } from "../../assets/register/chevron-left-solid.svg";
import { Container, Row, Col } from "react-bootstrap";

const USER_REGEX = /^[a-zA-Z][a-zA-Z0-9-_]{3,23}$/;
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;

const Register = () => {
  const userRef = useRef();
  const errRef = useRef();

  const [user, setUser] = useState("");
  const [validName, setValidName] = useState(false);
  const [userFocus, setUserFocus] = useState(false);

  const [pwd, setPwd] = useState("");
  const [validPwd, setValidPwd] = useState(false);
  const [pwdFocus, setPwdFocus] = useState(false);

  const [matchPwd, setMatchPwd] = useState("");
  const [validMatch, setValidMatch] = useState(false);
  const [matchPwdFocus, setMatchPwdFocus] = useState(false);

  const [errMsg, setErrMsg] = useState("");
  const [success, setSuccess] = useState(false);

  const [firstName, setFirstName] = useState("");
  const [firstNameFocus, setFirstNameFocus] = useState(false);

  const [lastName, setLastName] = useState("");
  const [lastNameFocus, setLastNameFocus] = useState(false);

  const [email, setEmail] = useState("");
  const [emailFocus, setEmailFocus] = useState(false);

  const [phone, setPhone] = useState("");
  const [phoneFocus, setPhoneFocus] = useState(false);

  const [tcNo, setTcNo] = useState("");
  const [tcNoFocus, setTcNoFocus] = useState(false);

  const [showPassword, setShowPassword] = useState(false);

  useEffect(() => {
    userRef.current.focus();
  }, []);

  useEffect(() => {
    const result = USER_REGEX.test(user);
    console.log(result);
    console.log(user);
    setValidName(result);
  }, [user]);

  useEffect(() => {
    const result = PWD_REGEX.test(pwd);
    console.log(result);
    console.log(pwd);
    setValidPwd(result);
    const match = pwd === matchPwd;
    setValidMatch(match);
  }, [pwd, matchPwd]);

  useEffect(() => {
    setErrMsg(" ");
  }, [user, pwd, matchPwd]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    // if button enabled with JS hack
    const v1 = USER_REGEX.test(user);
    const v2 = PWD_REGEX.test(pwd);

    if (!v1 || !v2) {
      setErrMsg("Invalid Entry");
      return;
    }

    console.log(user, pwd);
    setSuccess(true);
  };

  const handleCheckInfo = async (e) => {
    e.preventDefault();

    // TODO:: Control for tckno and firstname lastname

    console.log(user, pwd);
    setSuccess(true);
  };

  const handleClickShowPassword = () => {
    setShowPassword(!showPassword);
  };

  const handleBackButton = () => {
    setSuccess(false);
    setValidName(false);
  };

  return (
    <>
      {success ? (
        <section>
          <Container>
            <Row>
              <Col sm={2}>
                <Button onClick={handleBackButton}>
                  <BackIcon width="1rem"></BackIcon>
                </Button>
              </Col>
              <Col sm={10}>
                <h2>Almost Done!</h2>
              </Col>
            </Row>
          </Container>

          <TextField
            id="firstName"
            label="First Name"
            margin="normal"
            variant="outlined"
            ref={userRef}
            autoComplete="off"
            onChange={(e) => setFirstName(e.target.value)}
            required
            onFocus={() => setFirstNameFocus(true)}
            onBlur={() => setFirstNameFocus(false)}
          />

          <TextField
            id="lastName"
            label="Last Name"
            margin="normal"
            variant="outlined"
            ref={userRef}
            autoComplete="off"
            onChange={(e) => setLastName(e.target.value)}
            required
            onFocus={() => setLastNameFocus(true)}
            onBlur={() => setLastNameFocus(false)}
          />

          <TextField
            id="email"
            label="E-mail"
            margin="normal"
            variant="outlined"
            ref={userRef}
            autoComplete="off"
            onChange={(e) => setEmail(e.target.value)}
            required
            onFocus={() => setEmailFocus(true)}
            onBlur={() => setEmailFocus(false)}
          />

          <TextField
            id="phone"
            label="Phone Number"
            margin="normal"
            variant="outlined"
            ref={userRef}
            autoComplete="off"
            onChange={(e) => setPhone(e.target.value)}
            required
            onFocus={() => setPhoneFocus(true)}
            onBlur={() => setPhoneFocus(false)}
          />

          <TextField
            id="tckno"
            label="TC-Kimlik No"
            margin="normal"
            variant="outlined"
            ref={userRef}
            autoComplete="off"
            onChange={(e) => setTcNo(e.target.value)}
            required
            onFocus={() => setTcNoFocus(true)}
            onBlur={() => setTcNoFocus(false)}
          />

          <br></br>
          <Button
            disabled={
              firstName === "" ||
              lastName === "" ||
              email === "" ||
              phone === "" ||
              tcNo === ""
                ? true
                : false
            }
            variant="outlined"
            onClick={handleCheckInfo}
          >
            Check
          </Button>
        </section>
      ) : (
        <section>
          <h1>Register</h1>
          <br></br>
          <form onSubmit={handleSubmit}>
            {/* Username */}

            <TextField
              id="username"
              label="User Name"
              margin="normal"
              variant="outlined"
              ref={userRef}
              autoComplete="off"
              onChange={(e) => setUser(e.target.value)}
              required
              onFocus={() => setUserFocus(true)}
              onBlur={() => setUserFocus(false)}
              error={!validName && userFocus}
              helperText={
                !validName && userFocus ? (
                  <p>
                    <FontAwesomeIcon icon={faInfoCircle} />
                    4 to 24 characters. <br />
                    Must begin with a letter.
                    <br />
                    Letters, numbers, underscores, hyphens allowed.
                  </p>
                ) : (
                  ""
                )
              }
            />

            {/* Password */}

            <br></br>

            <FormControl /*sx={{ m: 1, width: "25ch" }}*/ variant="outlined">
              <InputLabel
                htmlFor="outlined-adornment-password"
                required
                error={!validPwd && pwdFocus}
              >
                Password
              </InputLabel>
              <OutlinedInput
                id="password"
                type={showPassword ? "text" : "password"}
                label="Password"
                onChange={(e) => setPwd(e.target.value)}
                value={pwd}
                required
                onFocus={() => setPwdFocus(true)}
                onBlur={() => setPwdFocus(false)}
                error={!validPwd && pwdFocus}
                helperText={
                  !validPwd && pwdFocus ? (
                    <p>
                      <FontAwesomeIcon icon={faInfoCircle} />
                      8 to 24 characters.
                      <br />
                      Must include uppercase and lowercase letters, a number and
                      a special character.
                      <br />
                      Allowed special characters:{"!@#$%"}
                    </p>
                  ) : (
                    ""
                  )
                }
                endAdornment={
                  <InputAdornment position="end">
                    <IconButton
                      aria-label="toggle password visibility"
                      onClick={handleClickShowPassword}
                      edge="end"
                    >
                      {showPassword ? <VisibilityOff /> : <Visibility />}
                    </IconButton>
                  </InputAdornment>
                }
              />
              {!validPwd && pwdFocus ? (
                <FormHelperText>
                  <p style={{ color: "red" }}>
                    <FontAwesomeIcon icon={faInfoCircle} />
                    8 to 24 characters.
                    <br />
                    Must include uppercase and lowercase letters, a number and a
                    special character.
                    <br />
                    Allowed special characters:{" "}
                    <span aria-label="exclamation mark">!</span>{" "}
                    <span aria-label="at symbol">@</span>{" "}
                    <span aria-label="hashtag">#</span>{" "}
                    <span aria-label="dollar sign">$</span>{" "}
                    <span aria-label="percent">%</span>
                  </p>
                </FormHelperText>
              ) : (
                ""
              )}
            </FormControl>

            <br></br>

            {/* Confirm Password */}

            <FormControl /*sx={{ m: 1, width: "25ch" }}*/ variant="outlined">
              <InputLabel
                htmlFor="outlined-adornment-password"
                required
                error={!validMatch && matchPwdFocus}
              >
                Confirm
              </InputLabel>
              <OutlinedInput
                id="confirm_pwd"
                type={showPassword ? "text" : "password"}
                label="Password"
                onChange={(e) => setMatchPwd(e.target.value)}
                value={matchPwd}
                required
                aria-invalid={validMatch ? "false" : "true"}
                aria-describedby="confirmnote"
                onFocus={() => setMatchPwdFocus(true)}
                onBlur={() => setMatchPwdFocus(false)}
                error={!validMatch && matchPwdFocus}
                endAdornment={
                  <InputAdornment position="end">
                    <IconButton
                      aria-label="toggle password visibility"
                      onClick={handleClickShowPassword}
                      edge="end"
                    >
                      {showPassword ? <VisibilityOff /> : <Visibility />}
                    </IconButton>
                  </InputAdornment>
                }
              />
              {!validMatch && matchPwdFocus ? (
                <FormHelperText>
                  <p style={{ color: "red" }}>
                    <FontAwesomeIcon icon={faInfoCircle} />
                    Must match the first password input field.
                  </p>
                </FormHelperText>
              ) : (
                ""
              )}
            </FormControl>

            <br></br>
            <Button
              disabled={!validName || !validPwd || !validMatch ? true : false}
              variant="outlined"
              onClick={handleSubmit}
            >
              Sign Up
            </Button>
          </form>

          <p>
            Already registered?
            <br />
            <span className="line">
              {/*put router link here*/}
              <a href="#">Sign In</a>
            </span>
          </p>
        </section>
      )}
    </>
  );
};

export default Register;
