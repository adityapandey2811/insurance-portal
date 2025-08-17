import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getFeedbacks } from "../../redux/actions/feedbackActions";
import { Card, Typography } from "@material-tailwind/react";
import NavigationBar from "../../components/NavigationBar";
import Footer from "../../components/Footer";
import { Link } from "react-router-dom";

const FeedbackList = () => {
  const dispatch = useDispatch();
  const feedbacks = useSelector((state) => state.feedback.feedbacks ?? []);

  const tableHeadings = [
    "Feedback Id",
    "Order Id",
    "Feedback Date",
    "Feedback Desciption",
  ];
  useEffect(() => {
    dispatch(getFeedbacks());
  }, [dispatch]);

  console.log(feedbacks);
  return (
    <div>
      <NavigationBar />
      <div className="container mx-auto my-10">
        <Card className="h-full w-full overflow-scroll mb-4">
          <table className="w-full min-w-max table-auto text-left">
            <thead>
              <tr>
                {tableHeadings.map((head) => (
                  <th
                    key={head}
                    className="border-b border-blue-gray-100 bg-blue-gray-50 p-4"
                  >
                    <Typography
                      variant="small"
                      color="blue-gray"
                      className="font-normal leading-none opacity-70"
                    >
                      {head}
                    </Typography>
                  </th>
                ))}
              </tr>
            </thead>
            <tbody>
              {feedbacks.map(
                ({
                  orderId,
                  feedbackDescription,
                  feedbackId,
                  feedbackDate,
                }) => (
                  <tr key={feedbackId} className="even:bg-blue-gray-50/50">
                    <td className="p-4">
                      <Typography
                        variant="small"
                        color="blue-gray"
                        className="font-normal"
                      >
                        {feedbackId}
                      </Typography>
                    </td>

                    <td className="p-4">
                      <Typography
                        variant="small"
                        color="blue-gray"
                        className="font-normal"
                      >
                        {orderId}
                      </Typography>
                    </td>

                    <td className="p-4">
                      <Typography
                        variant="small"
                        color="blue-gray"
                        className="font-normal"
                      >
                        {feedbackDate}
                      </Typography>
                    </td>

                    <td className="p-4">
                      <Typography
                        variant="small"
                        color="blue-gray"
                        className="font-normal"
                      >
                        {feedbackDescription}
                      </Typography>
                    </td>
                  </tr>
                )
              )}
            </tbody>
          </table>
        </Card>
        <Link
          to="/admin"
          className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
        >
          Back to Admin Page
        </Link>
      </div>
      <Footer />
    </div>
  );
};

export default FeedbackList;
