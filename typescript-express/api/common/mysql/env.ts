import { ConnectionConfig } from "mysql";
import mysql from "mysql";

const con: ConnectionConfig = {
  // mysql 접속 설정
  host: "localhost",
  port: 11500,
  user: "root",
  password: "zigzag",
  database: "express",
};
export const connection = mysql.createConnection(con);

export const db_connect = () => {
  return connection.connect();
};
