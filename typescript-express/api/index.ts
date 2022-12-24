import express from "express";
import BankRoutes from "./routes/BankRoutes";

const app = express();
app.use("/banks", BankRoutes);

app.listen(5000);
