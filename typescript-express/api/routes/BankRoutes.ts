import { Router } from "express";
import { BankController } from "../controllers/BankController";

const router: Router = Router();

router.get("/", BankController.registerBankAccount);

export default router;
