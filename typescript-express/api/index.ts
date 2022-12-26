import app from "./app";
import * as db from "./common/mysql/env";

const PORT = process.env.PORT || 8088;
db.db_connect();
app.listen(PORT, () => {
  console.log(`Server is running http://localhost:${PORT}`);
});
