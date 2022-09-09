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
  Snackbar,
  Alert,
  Dialog,
  DialogTitle,
} from "@mui/material";
import { Visibility, VisibilityOff } from "@mui/icons-material";
import { ReactComponent as BackIcon } from "../../assets/register/chevron-left-solid.svg";
import { Container, Row, Col } from "react-bootstrap";
import axios from "../../services/axios";
import InputMask from "react-input-mask";

const USER_REGEX = /^[a-zA-Z][a-zA-Z0-9-_]{3,23}$/;
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;
const TCNO_REGEX = /^[1-9]{1}[0-9]{9}[02468]{1}$/;
const PHONE_NUMBER_REGEX =
  /^(5)([0-9]{2})\s?([0-9]{3})\s?([0-9]{2})\s?([0-9]{2})$/;
const EMAIL_REGEX = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
const BIRTH_YEAR_REGEX = /^(19|20)\d{2}$/;

const BASE_URL = "/api/user";
const REGISTER_URL = BASE_URL + "/register";
const VERIFY_ACCOUNT_URL = BASE_URL + "/verifyAccount";

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
  const [validEmail, setValidEmail] = useState(false);
  const [emailFocus, setEmailFocus] = useState(false);

  const [phone, setPhone] = useState("");
  const [validPhone, setValidPhone] = useState(false);
  const [phoneFocus, setPhoneFocus] = useState(false);

  const [tcNo, setTcNo] = useState("");
  const [validTcNo, setValidTcNo] = useState(false);
  const [tcNoFocus, setTcNoFocus] = useState(false);

  const [birthYear, setBirthYear] = useState(null);
  const [validBirthYear, setValidBirthYear] = useState(false);
  const [birthYearFocus, setBirthYearFocus] = useState(false);

  const [showPassword, setShowPassword] = useState(false);

  const [snackbar, setSnackbar] = useState({
    open: false,
    vertical: "top",
    horizontal: "center",
    message: "",
    status: "error",
  });

  const { vertical, horizontal, open } = snackbar;

  const [verificationDialogOpen, setVerificationDialogOpen] = useState(false);

  const [verificationCode, setVerificationCode] = useState("");

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

  useEffect(() => {
    const result = EMAIL_REGEX.test(email);
    console.log(result);
    console.log(email);
    setValidEmail(result);
  }, [email]);

  useEffect(() => {
    const result = TCNO_REGEX.test(tcNo);
    console.log(result);
    console.log(tcNo);
    setValidTcNo(result);
  }, [tcNo]);

  useEffect(() => {
    const result = BIRTH_YEAR_REGEX.test(birthYear);
    console.log(result);
    console.log(tcNo);
    setValidBirthYear(result);
  }, [birthYear]);

  useEffect(() => {
    var phoneNumber = "";
    if (phone.length === 14) {
      phoneNumber =
        phone.substring(1, 4) +
        phone.substring(6, 9) +
        phone.substring(10, phone.length);
    }

    const result = PHONE_NUMBER_REGEX.test(phoneNumber);
    console.log(result);
    console.log(phoneNumber);
    setValidPhone(result);
  }, [phone]);

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

    var phoneNumber =
      phone.substring(1, 4) +
      phone.substring(6, 9) +
      phone.substring(10, phone.length);

    try {
      const response = await axios.post(
        REGISTER_URL,
        JSON.stringify({
          userName: user,
          firstName,
          lastName,
          password: pwd,
          email: email,
          phone: phoneNumber,
          tckno: tcNo,
          birthYear: birthYear,
        }),
        {
          headers: { "Content-Type": "application/json" },
          withCredentials: false,
        }
      );

      setSnackbar({
        open: true,
        vertical: "top",
        horizontal: "left",
        message: "Registration Success",
        status: "success",
      });

      // When the registration is successful, it sends a confirmation email to verify the email address.
      setVerificationDialogOpen(true);

      // TODO:: Clear input fields
    } catch (err) {
      console.log("hata");
      if (!err?.response) {
        setSnackbar({
          open: true,
          vertical: "top",
          horizontal: "left",
          message: "No Server Response",
          status: "error",
        });
        setErrMsg("No Server Response");
      } else if (err.response?.status === 409) {
        setSnackbar({
          open: true,
          vertical: "top",
          horizontal: "left",
          message: "Username Taken",
          status: "error",
        });
        setErrMsg("Username Taken");
      } else if (err.response?.status === 423) {
        setSnackbar({
          open: true,
          vertical: "top",
          horizontal: "left",
          message: "User Not Activated.",
          status: "error",
        });
        setErrMsg("User Not Activated.");
      } else if (err.response?.status === 417) {
        setSnackbar({
          open: true,
          vertical: "top",
          horizontal: "left",
          message: "User information must be entered correctly.",
          status: "error",
        });
        setErrMsg("User information must be entered correctly.");
      } else {
        setSnackbar({
          open: true,
          vertical: "top",
          horizontal: "left",
          message: "Registration Failed",
          status: "error",
        });
        setErrMsg("Registration Failed");
      }
      //errRef.current.focus();
    }
  };

  const handleClickShowPassword = () => {
    setShowPassword(!showPassword);
  };

  const handleBackButton = () => {
    setSuccess(false);
    setValidName(false);
  };

  const handleSnackbarClose = () => {
    setSnackbar({ open: false, vertical: "top", horizontal: "center" });
  };

  const handleVerificationDialogClose = () => {
    setVerificationDialogOpen(false);
  };

  const handleVerificationCodeEntered = async () => {
    setUser("emin");
    console.log("userds: " + user);
    console.log("aasdsfd: " + verificationCode);
    try {
      const response = await axios.post(
        VERIFY_ACCOUNT_URL,
        JSON.stringify({
          userName: user,
          verificationCode: verificationCode,
        }),
        {
          headers: { "Content-Type": "application/json" },
          withCredentials: false,
        }
      );

      setSnackbar({
        open: true,
        vertical: "top",
        horizontal: "left",
        message: "Account Verification Success. Please Log In",
        status: "success",
      });

      // When the registration is successful, it sends a confirmation email to verify the email address.
      setVerificationDialogOpen(false);
    } catch (err) {
      if (!err?.response) {
        setSnackbar({
          open: true,
          vertical: "top",
          horizontal: "left",
          message: "No Server Response",
          status: "error",
        });
        setErrMsg("No Server Response");
      } else if (err.response?.status === 208) {
        setSnackbar({
          open: true,
          vertical: "top",
          horizontal: "left",
          message: "User is Already Activated",
          status: "success",
        });
        setErrMsg("User is Already Activated");
      } else {
        setSnackbar({
          open: true,
          vertical: "top",
          horizontal: "left",
          message: "Verification Failed",
          status: "error",
        });
        setErrMsg("Verification Failed");
      }
    }
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
            autoComplete="off"
            onChange={(e) => setEmail(e.target.value)}
            required
            onFocus={() => setEmailFocus(true)}
            onBlur={() => setEmailFocus(false)}
            error={!validEmail && emailFocus}
            helperText={
              !validEmail && emailFocus ? (
                <p>
                  <FontAwesomeIcon icon={faInfoCircle} />
                  It must be real email address
                </p>
              ) : (
                ""
              )
            }
          />

          <InputMask
            mask="(999) 999-9999"
            onChange={(e) => setPhone(e.target.value)}
            onBlur={() => setPhoneFocus(false)}
            onFocus={() => setPhoneFocus(true)}
            required
          >
            {(inputProps) => (
              <TextField
                id="phone"
                label="Phone Number"
                margin="normal"
                variant="outlined"
                autoComplete="off"
                maxLength={10}
                error={!validPhone && phoneFocus}
                helperText={
                  !validPhone && phoneFocus ? (
                    <p>
                      <FontAwesomeIcon icon={faInfoCircle} />
                      10 character numbers. <br />
                      It must be start with 5
                    </p>
                  ) : (
                    ""
                  )
                }
              />
            )}
          </InputMask>

          <TextField
            id="tckno"
            label="TC-Kimlik No"
            margin="normal"
            variant="outlined"
            type="text"
            autoComplete="off"
            onChange={(e) => setTcNo(e.target.value)}
            required
            onFocus={() => setTcNoFocus(true)}
            onBlur={() => setTcNoFocus(false)}
            inputProps={{ minLength: 11, maxLength: 11 }}
            error={!validTcNo && tcNoFocus}
            helperText={
              !validTcNo && tcNoFocus ? (
                <p>
                  <FontAwesomeIcon icon={faInfoCircle} />
                  11 character numbers. <br />
                  It must be real Tc Kimlik No
                </p>
              ) : (
                ""
              )
            }
          />

          <TextField
            id="birthYear"
            label="Birth Year"
            margin="normal"
            variant="outlined"
            autoComplete="off"
            onChange={(e) => setBirthYear(e.target.value)}
            required
            onFocus={() => setBirthYearFocus(true)}
            onBlur={() => setBirthYearFocus(false)}
            error={!validBirthYear && birthYearFocus}
            helperText={
              !validBirthYear && birthYearFocus ? (
                <p>
                  <FontAwesomeIcon icon={faInfoCircle} />4 character numbers
                </p>
              ) : (
                ""
              )
            }
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

          <Snackbar
            open={open}
            autoHideDuration={6000}
            onClose={handleSnackbarClose}
            anchorOrigin={{ vertical, horizontal }}
            key={vertical + horizontal}
          >
            <Alert
              onClose={handleSnackbarClose}
              severity={snackbar.status}
              sx={{ width: "100%" }}
            >
              {snackbar.message}
            </Alert>
          </Snackbar>

          <Dialog
            onClose={handleVerificationDialogClose}
            open={verificationDialogOpen}
          >
            <DialogTitle>Email Verification</DialogTitle>
            <div className="verification">
              <p style={{ fontSize: 20 }}>
                We will send verification code on your email
              </p>
              <TextField
                id="verificationCode"
                label="Verification Code"
                margin="normal"
                variant="outlined"
                autoComplete="off"
                onChange={(e) => setVerificationCode(e.target.value)}
                required
              />
              <br></br>
              <Button
                style={{ marginBottom: 10, fontSize: 15 }}
                disabled={verificationCode.length != 6}
                variant="outlined"
                onClick={handleVerificationCodeEntered}
              >
                Verify Account
              </Button>
              <br></br>
              <p style={{ fontSize: 17 }}>Didn't receive code? Resend</p>
            </div>
          </Dialog>
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
