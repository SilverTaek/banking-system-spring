import express from "express";
import "reflect-metadata";
import cors from "cors";
import bankRouter from "./routes/BankRoutes";

const app = express();
app.use(cors());
app.use(express.json());

app.get("/", (req, res) => {
  res.send("Success Connect");
});

app.use("/banks", bankRouter);

export default app;
