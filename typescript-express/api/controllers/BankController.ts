import { BankService } from "../service/BankService";
import { Request, Response } from "express";

export class BankController {
  static registerBankAccount(req: Request, res: Response) {
    res.send("계좌 등록에 성공했습니다.");
    // return BankService.registerBankAccount();
  }
}
