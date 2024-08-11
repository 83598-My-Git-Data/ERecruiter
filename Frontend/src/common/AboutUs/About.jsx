import "./About.css";
import HrAndEmpl from "../../../assets/images/Manager&FemaleBoss.jpg";
import BrainStorming from "../../../assets/images/EmployeesBrainStorming.jpg";
import EasyManagment from "../../../assets/images/easyManagement.jpg";
function About() {
  return (
    <div className="container container-background">
      <div className="row">
        <div className="col-sm-4">
          <div className="card">
            <img
              className="card-img-top"
              src={HrAndEmpl}
              alt="hr and employee"
            />
            <div className="card-body">
              <p className="card-text">
                We value the importance of a supportive and accessible Human
                Resources team. Our HR professionals are here to listen, guide,
                and address your concerns. Whether you have questions about
                benefits, career development, or any other aspect of your
                journey with us, our doors – virtual or physical – are always
                open. We believe that by facilitating easy communication with
                HR, we can create a workplace where every team member feels
                heard, valued, and empowered.
              </p>
            </div>
          </div>
        </div>
        <div className="col-sm-4">
          <div className="card">
            <img
              className="card-img-top"
              src={BrainStorming}
              alt='employess brainstorming'
            />
            <div className="card-body">
              <p className="card-text">
                Communication is at the heart of any successful organization. At
                Get Hired, we foster an environment of Open Communication, where
                every team member is encouraged to express their ideas, share
                feedback, and connect with our dedicated HR team. We believe
                that transparent communication not only strengthens our internal
                relationships but also enhances collaboration and innovation.
              </p>
            </div>
          </div>
        </div>
        <div className="col-sm-4">
          <div className="card">
            <img
              className="card-img-top"
              src={EasyManagment}
              alt='easy management'
            />
            <div className="card-body">
              <p className="card-text">
                At Get Hired, we understand the importance of
                streamlined and efficient operations. Our commitment to Easy
                Management is reflected in everything we do. From user-friendly
                interfaces to simplified processes, we strive to make every
                aspect of your experience with us hassle-free. We believe that
                when tasks are easy to manage, individuals can focus more on
                what truly matters – their professional growth and success
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default About;
