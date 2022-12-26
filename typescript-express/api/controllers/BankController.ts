import { autoInjectable } from "tsyringe";
import BankService from "../service/BankService";
import BaseController from "./BaseController";

@autoInjectable()
export default class BankController extends BaseController {
  constructor(bankService: BankService) {
    super(bankService);
  }
}
