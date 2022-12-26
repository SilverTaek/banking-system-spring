import { injectable } from "tsyringe";
import BaseService from "../service/BaseService";
import { Request, Response } from "express";

@injectable()
export default class BaseController {
  private readonly baseService: BaseService;
  constructor(baseService: BaseService) {
    this.baseService = baseService;
  }

  create = (req: Request, res: Response) => {
    const result = this.baseService.create(req.body);
    return res.send(result);
  };
}
