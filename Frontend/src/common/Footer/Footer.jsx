import "./footer.css";
import { Link } from "react-router-dom";
const date = new Date();
function Footer() {
  return (
    <div>
      <footer className="text-center text-lg-start bg-body-tertiary text-muted">
        <section class="border-top">
          <div class="container text-center text-md-start mt-1 ">
            <div class="row mt-1">
              <div class="col-md-3 col-lg-12 col-xl-12 mx-auto mb-4">
                <h6 class="text-uppercase fw-bold mb-4">E Recruiter</h6>
                <p>
                  <Link
                    className="link-offset-2 link-underline link-underline-opacity-0 link"
                    to={"/contact-us"}
                  >
                    ContactUs |
                  </Link>
                  {"  "}
                  <Link
                    className="link-offset-2 link-underline link-underline-opacity-0 link"
                    to={"/ about-us"}
                  >
                    AboutUs
                  </Link>
                </p>
              </div>
            </div>
          </div>
        </section>
        <div
          class="text-center p-4"
          style={{ backgroundColor: "rgba(0, 0, 0, 0.05)" }}
        >
          © {date.getFullYear()} Copyright: ERecruiter.All rights reserved
        </div>
      </footer>
    </div>
  );
}

export default Footer;
