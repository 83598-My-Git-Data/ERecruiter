import Footer from "../Footer/Footer";
import "../Home/Home.css";
function Home() {
  return (
    <div style={{marginTop:40}}>
      <div className="home-background">
        <div className="content">
          <div className="title">
            <h1>ERecruiter!</h1>
          </div>
          <div className="tag-line">
            ERecruiter: Bridging Careers, Fostering Talent – Your Gateway to
            Professional Success!
          </div>
          <div className="paragraph">
            At ERecruiter, our mission is to connect exceptional talent with
            rewarding career opportunities within our organization. We are
            committed to providing a seamless and transparent job portal that
            streamlines our hiring processes and enables us to attract, nurture,
            and retain top-tier talent. Upholding our values of putting
            employees first, fostering innovation, and promoting diversity, we
            aim to build a thriving work environment that aligns with the career
            goals of our team members. Our company job portal is a testament to
            our dedication to creating a workplace where every individual can
            excel and contribute to the success of our organization.
          </div>
          <hr></hr>
          <p className="join-us">SignUp and Start <span className="applying">Applying</span></p>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default Home;
