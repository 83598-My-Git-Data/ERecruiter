import { useEffect, useState } from "react";
import { ResponsiveContainer, BarChart, Bar, XAxis, YAxis,Tooltip } from "recharts";
import { getReport } from "../../services/admin";

function Report() {
  const [data, setData] = useState({
    "totalNumberOfHr": 0,
    "activeHr": 0,
    "totalNumberOfJobs": 0,
    "openJobs": 0,
  });


  /*Mapping the json object to array to use in bar graph*/
  const dataArray = Object.entries(data).map(([name, count]) => ({ name, count }));

  useEffect(() => {
    getReport()
      .then((response) => {
        setData(response);
    })
      .catch((error) => {
        console.error("error fetching data");
      });
  }, []);

  return (
    <>
      <h2 className="mt-2">Active,inactive and total Jobs and HR</h2>
      <hr />
      <div>
      <ResponsiveContainer width={"50%"} aspect={1.5}>
        <BarChart data={dataArray} width={400} height={400}>
          <XAxis dataKey="name"></XAxis>
          <YAxis ></YAxis>
          <Tooltip/>
          <Bar dataKey="count" fill={"#9b7ed9"} width={200}></Bar>
        </BarChart>
      </ResponsiveContainer>
      </div>
    </>
  );
}

export default Report;
