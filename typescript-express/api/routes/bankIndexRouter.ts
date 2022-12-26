import { Router } from "express";
import bankRouter from "./BankRoutes";

const bankIndexRouter = Router();

bankIndexRouter.use("/bank", bankRouter);

export default bankIndexRouter;
