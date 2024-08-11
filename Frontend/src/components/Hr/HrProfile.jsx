import { useEffect, useState } from "react";
import { getHrDetails, uploadImage,upDateImage,removeImage } from "../../services/hr";
import ImageAlternate from "../../assets/images/ProfilePicAlternate.png";
import { ToastContainer, toast } from "react-toastify";
export const HrProfile = () => {
  /*Holds hr details*/
  const [hrDetails, setHrDetails] = useState({
    id: 0,
    qualification: "",
    status: true,
    imageURL: "",
    user: {
      firstName: "",
      lastName: "",
      gender: "",
      email: "",
      phoneNumber: "",
      dob: "",
    },
  });

  //state to hold the image
  const [image, setImage] = useState(null);

  //upload image
  const uploadProfileImage = () => {
    uploadImage(image)
      .then((response) => {
        toast.success("Image uploaded successfully");
      })
      .catch(() => {
        toast.error("something happened bad")
      });
  };

   //upadate image
   const updateProfileImage = () => {
    upDateImage(image)
      .then((response) => {
        toast.success("Image updated successfully");
      })
      .catch(() => {
        toast.error("something happened bad")
      });
  }

  //remove image 
  const removeProfilePic=()=>{
    removeImage().then((response=>{
      toast.success("image removed successfully")
    })).catch(()=>{
      toast.error("something happened bad")
    })
  }

  // Fetch data from getHrDetails when the component mounts
  const fetchData = async () => {
    try {
      const response = await getHrDetails();
      setHrDetails(response);
    } catch (error) {
      console.error("Error fetching HR details:", error);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);
  return (
    <div className="p-2">
      <div className={"card w-100 vh-50"}>
        <div className={"row no-gutters"}>
          <div className={"col-md-8"}>
            <div className={"card-body"}>
              <h4 className={"card-title"}>Profile Details</h4>
              <p className={"card-text"}>
                <b>Name:</b>{" "}
                {`${hrDetails.user.firstName} ${hrDetails.user.lastName}`}
              </p>
              <p className={"card-text"}>
                <b>Email:</b>
                {" " + hrDetails.user.email}
              </p>
              <p className={"card-text"}>
                <b>Qualification:</b> {hrDetails.qualification}
              </p>
              <p className={"card-text"}>
                <b>Status:</b>
                {hrDetails.status ? "Active" : "Deactivated"}
              </p>
              <p className={"card-text"}></p>
            </div>
          </div>
          <div className={"col-md-4"}>
            <div className={"border m-2 p-2 w-auto"}>
              {hrDetails.imageURL === "deleted" ? (
                /*Upload image */
                <div>
                  <div className={"mb-4 d-flex justify-content-center"}>
                    <img
                      id="selectedImage"
                      src={ImageAlternate}
                      alt="example placeholder"
                      style={{ width: 300 }}
                    />
                  </div>
                  <div class={"d-flex justify-content-center"}>
                    <div className="">
                      <label className={"form-label"}>Upload Image</label>
                      <input
                        type="file"
                        className={"form-control btn btn-rounded"}
                        onChange={(event) => {
                          setImage(event.target.files[0]);
                        }}
                      />
                    </div>
                  </div> 
                  {image !== null ? (
                    <div className={"text-center"}>
                      <button
                        className={"btn m-2"}
                        onClick={() => {
                          uploadProfileImage();
                        }}
                      >
                        Upload Image
                      </button>
                    </div>
                  ) : (
                    <>
                    </>
                  )}
                </div>
              ) : (
                /*Update Image*/
                <>
                <div className={"mb-4 d-flex justify-content-center"}>
                    <img
                      id="selectedImage"
                      src={hrDetails.imageURL}
                      alt="example placeholder"
                      style={{ width: 300 }}
                    />
                  </div>
                  <div class={"d-flex justify-content-center"}>
                    <div className="">
                      <label className={"form-label"}>Update Image</label>
                      <input
                        type="file"
                        className={"form-control btn btn-rounded"}
                        onChange={(event) => {
                          setImage(event.target.files[0]);
                        }}
                      />
                    </div>
                    <div className={"text-center"}>
                      <button
                        className={"btn btn-danger m-2"}
                        onClick={() => {
                          removeProfilePic();
                        }}
                      >
                        Remove Image
                      </button>
                    </div>  
                  </div>
                  {image !== null ? (
                    <div className={"text-center"}>
                      <button
                        className={"btn m-2"}
                        onClick={() => {
                          updateProfileImage();
                        }}
                      >
                        Upadte Image
                      </button>
                    </div>
                  ) : (
                    <></>
                  )}
                </>
              )}
            </div>
          </div>
        </div>
      </div>
      <ToastContainer />
    </div>
  );
};
