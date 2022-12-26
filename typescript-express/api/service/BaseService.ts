import { connection } from "../common/mysql/env";

export default class BaseService {
  create = (req: Request) => {
    const query_builder =
      'INSERT INTO bank (id, bank_account, bank_number) values (1, "1234", "1111")';

    connection.query(query_builder);

    connection.end();
  };
}
