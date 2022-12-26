import { Router } from "express";
import BankController from "../controllers/BankController";
import BankService from "../service/BankService";

const bankRouter = Router();

const bankService = new BankService();
const bankController = new BankController(bankService);

bankRouter.post("/", bankController.create);

export default bankRouter;
