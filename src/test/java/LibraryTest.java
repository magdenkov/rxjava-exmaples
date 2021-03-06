/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import com.github.jhonnymertz.wkhtmltopdf.wrapper.Pdf;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.params.Param;
import com.google.gson.Gson;
import com.squareup.okhttp.*;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class LibraryTest {

    public static final String SOURCE = "<html>\n" +
            "<head>\n" +
            "    <title>Welcome!</title>\n" +
            "    <style>\n" +
            "        body,html{width:100%;height:100%;margin:0;padding:0}body{font:16px \"Open Sans\",sans-serif;color:#666c6f;}body *{box-sizing:border-box}.container{padding:20px}.header{text-align:center;margin-bottom:40px;}.header .logo{display:inline-block;background-image:url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHN2ZyB3aWR0aD0iMTI0cHgiIGhlaWdodD0iODRweCIgdmlld0JveD0iMCAwIDEyNCA4NCIgdmVyc2lvbj0iMS4xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIj4KICAgIDxkZWZzPjwvZGVmcz4KICAgIDxnIGlkPSJQTS0tLURlcG9zaXQtQWNjb3VudGluZyIgc3Ryb2tlPSJub25lIiBzdHJva2Utd2lkdGg9IjEiIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0iZXZlbm9kZCI+CiAgICAgICAgPGcgaWQ9IkRlcG9zaXQtQWNjb3VudGluZy1QTS0tLTQtU3RhdGVtZW50LVJlZnVuZCIgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoLTc3My4wMDAwMDAsIC0xODQuMDAwMDAwKSIgZmlsbC1ydWxlPSJub256ZXJvIj4KICAgICAgICAgICAgPGcgaWQ9IlBhZ2UtSGVhZGVyIiB0cmFuc2Zvcm09InRyYW5zbGF0ZSgyNjAuMDAwMDAwLCAxODQuMDAwMDAwKSI+CiAgICAgICAgICAgICAgICA8ZyBpZD0iTVlORF9fTG9nbyIgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoNTEzLjAwMDAwMCwgMC4wMDAwMDApIj4KICAgICAgICAgICAgICAgICAgICA8cG9seWdvbiBpZD0iU2hhcGUiIGZpbGw9IiMwMTE2MzgiIHBvaW50cz0iMjIuNzY4MTI1NyA4My43NjAzMDE4IDE3LjkzODk0NSA4My43NjAzMDE4IDE3LjkzODk0NSA3MC40Njc3ODQ4IDEzLjMyNTQ3NyA4My43NjAzMDE4IDkuNjU4MzYxMzkgODMuNzYwMzAxOCA0Ljg5MTgwNjk2IDcwLjQ2Nzc4NDggNC44OTE4MDY5NiA4My43NjAzMDE4IDAuMDYyNjI2MjYyNiA4My43NjAzMDE4IDAuMDYyNjI2MjYyNiA2Mi4zMTc4MzIgNS43NzU1MzMxMSA2Mi4zMTc4MzIgMTEuNDg4NDQgNzcuMjM2MTUzIDE3LjEzODcyMDUgNjIuMzE3ODMyIDIyLjc2MTE2NzIgNjIuMzE3ODMyIDIyLjc2MTE2NzIgODMuNzYwMzAxOCI+PC9wb2x5Z29uPgogICAgICAgICAgICAgICAgICAgIDxwb2x5Z29uIGlkPSJTaGFwZSIgZmlsbD0iIzAxMTYzOCIgcG9pbnRzPSI0Ni4yNTk5MzI3IDgzLjc2MDMwMTggNDAuNzkwNTcyNCA4My43NjAzMDE4IDQ0LjcyOTA2ODUgNzUuNzA4MDM2OSAzNy4xNTgyNDkyIDYyLjMxNzgzMiA0Mi45MzM3ODIzIDYyLjMxNzgzMiA0Ny40ODQ2MjQgNzAuNzM5OTE1IDUxLjM5NTI4NjIgNjIuMzE3ODMyIDU2Ljg2NDY0NjUgNjIuMzE3ODMyIj48L3BvbHlnb24+CiAgICAgICAgICAgICAgICAgICAgPHBvbHlnb24gaWQ9IlNoYXBlIiBmaWxsPSIjMDExNjM4IiBwb2ludHM9IjgzLjk1Mzk4NDMgNzYuMjg3MTg1OSA4My45NTM5ODQzIDYyLjMxNzgzMiA4OC44NzM2MjUxIDYyLjMxNzgzMiA4OC44NzM2MjUxIDgzLjc2MDMwMTggODMuNzEwNDM3NyA4My43NjAzMDE4IDc2LjAxNDM2NTkgNjkuOTc5MzQ1OCA3Ni4wMTQzNjU5IDgzLjc2MDMwMTggNzEuMDY2ODkxMSA4My43NjAzMDE4IDcxLjA2Njg5MTEgNjIuMzE3ODMyIDc2LjIzMDA3ODYgNjIuMzE3ODMyIj48L3BvbHlnb24+CiAgICAgICAgICAgICAgICAgICAgPHBhdGggZD0iTTExMi44MTc3MzMsNjIuMzE3ODMyIEMxMTkuOTAxNDU5LDYyLjMxNzgzMiAxMjMuOTM3Mzc0LDY3LjU1ODA4NDIgMTIzLjkzNzM3NCw3My4wMzU1NzgxIEMxMjMuOTM3Mzc0LDc4LjUyMDA0OTcgMTE5LjkzNjI1MSw4My43NTMzMjQxIDExMi44MTc3MzMsODMuNzUzMzI0MSBMMTA0Ljk2ODU3NSw4My43NTMzMjQxIEwxMDQuOTY4NTc1LDYyLjMxMDg1NDMgTDExMi44MTc3MzMsNjIuMzEwODU0MyBMMTEyLjgxNzczMyw2Mi4zMTc4MzIgWiBNMTA5LjkxNjA0OSw2Ni42MDkxMTcxIEwxMDkuOTE2MDQ5LDc5LjQ3NTk5NDUgTDExMi41NzQxODYsNzkuNDc1OTk0NSBDMTE2LjU3NTMwOSw3OS40NzU5OTQ1IDExOC44OTk0MzksNzYuMzQ5OTg1MiAxMTguODk5NDM5LDczLjA0MjU1NTggQzExOC44OTk0MzksNjkuNzM1MTI2MyAxMTYuNTc1MzA5LDY2LjYwOTExNzEgMTEyLjU3NDE4Niw2Ni42MDkxMTcxIEwxMDkuOTE2MDQ5LDY2LjYwOTExNzEgWiIgaWQ9IlNoYXBlIiBmaWxsPSIjMDExNjM4Ij48L3BhdGg+CiAgICAgICAgICAgICAgICAgICAgPHBhdGggZD0iTTcyLjQ4NjQxOTgsMS4yMTQxMTk2NyBMNTkuNTY0NTM0MiwxMi40MDYzNDkzIEM1Ny4wNTk0ODM3LDE0LjU3NjQxMzcgNTUuNjI2MDM4MiwxNy43MjMzNTYxIDU1LjYyNjAzODIsMjEuMDQ0NzQwOSBMNTUuNjI2MDM4Miw0Mi4yNjM5MjQ0IEM1NS42MjYwMzgyLDQ0LjUyNDY5ODkgNTcuNDQ5MTU4Miw0Ni4zNTI4NTYxIDU5LjcwMzcwMzcsNDYuMzUyODU2MSBMNjQuODQ2MDE1Nyw0Ni4zNTI4NTYxIEM2Ny4xMDA1NjEyLDQ2LjM1Mjg1NjEgNjguOTIzNjgxMyw0NC41MjQ2OTg5IDY4LjkyMzY4MTMsNDIuMjYzOTI0NCBMNjguOTIzNjgxMywzMy4wNjAzMzkxIEM2OC45MjM2ODEzLDI5LjM3NjExMzkgNzEuOTAxOTA4LDI2LjM5NjYzNjMgNzUuNTY5MDIzNiwyNi4zOTY2MzYzIEw3NS43MTUxNTE1LDI2LjM5NjYzNjMgQzc5LjM4OTIyNTYsMjYuMzk2NjM2MyA4Mi4zNjA0OTM4LDI5LjM4MzA5MTYgODIuMzYwNDkzOCwzMy4wNjAzMzkxIEw4Mi4zNjA0OTM4LDQyLjI1Njk0NjcgQzgyLjM2MDQ5MzgsNDQuNTE3NzIxMiA4NC4xODM2MTM5LDQ2LjM0NTg3ODQgODYuNDM4MTU5NCw0Ni4zNDU4Nzg0IEw5MS43MjY1OTkzLDQ2LjM0NTg3ODQgQzkzLjk4MTE0NDgsNDYuMzQ1ODc4NCA5NS44MDQyNjQ5LDQ0LjUxNzcyMTIgOTUuODA0MjY0OSw0Mi4yNTY5NDY3IEw5NS44MDQyNjQ5LDE4LjAwOTQ0MTggQzk1LjgwNDI2NDksMTYuNjIwODc5NiA5NS4xOTE5MTkyLDE1LjI5NTExNjggOTQuMTQxMTg5NywxNC4zOTQ5OTM2IEw3OC42Mzc3MTA0LDEuMTg2MjA4ODcgQzc2Ljg2MzI5OTcsLTAuMzI3OTUxODY1IDc0LjI1Mzg3MjEsLTAuMzEzOTk2NDY2IDcyLjQ4NjQxOTgsMS4yMTQxMTk2NyBaIiBpZD0iU2hhcGUiIGZpbGw9IiMzNUJBN0QiPjwvcGF0aD4KICAgICAgICAgICAgICAgICAgICA8cGF0aCBkPSJNNDUuNjY4NDYyNCwxLjE1ODI5ODA4IEwzMC4xMDIzNTY5LDE0LjM5NDk5MzYgQzI5LjA0NDY2ODksMTUuMjk1MTE2OCAyOC40MzIzMjMyLDE2LjYyMDg3OTYgMjguNDMyMzIzMiwxOC4wMDk0NDE4IEwyOC40MzIzMjMyLDQyLjI0OTk2OSBDMjguNDMyMzIzMiw0NC41MTA3NDM1IDMwLjI1NTQ0MzMsNDYuMzM4OTAwNyAzMi41MDk5ODg4LDQ2LjMzODkwMDcgTDM4LjAyMTA5OTksNDYuMzM4OTAwNyBDNDAuMjc1NjQ1Myw0Ni4zMzg5MDA3IDQyLjA5ODc2NTQsNDQuNTEwNzQzNSA0Mi4wOTg3NjU0LDQyLjI0OTk2OSBMNDIuMDk4NzY1NCwzMy4wNjAzMzkxIEM0Mi4wOTg3NjU0LDI5LjM3NjExMzkgNDUuMDc2OTkyMSwyNi4zOTY2MzYzIDQ4Ljc0NDEwNzcsMjYuMzk2NjM2MyBMNDguODkwMjM1NywyNi4zOTY2MzYzIEM1Mi41NjQzMDk4LDI2LjM5NjYzNjMgNTUuNTM1NTc4LDI5LjM4MzA5MTYgNTUuNTM1NTc4LDMzLjA2MDMzOTEgTDU1LjUzNTU3OCw0Mi4yNTY5NDY3IEM1NS41MzU1NzgsNDQuNTE3NzIxMiA1Ny4zNTg2OTgxLDQ2LjM0NTg3ODQgNTkuNjEzMjQzNSw0Ni4zNDU4Nzg0IEw2NC45MDE2ODM1LDQ2LjM0NTg3ODQgQzY3LjE1NjIyOSw0Ni4zNDU4Nzg0IDY4Ljk3OTM0OSw0NC41MTc3MjEyIDY4Ljk3OTM0OSw0Mi4yNTY5NDY3IEw2OC45NzkzNDksMTguMDA5NDQxOCBDNjguOTc5MzQ5LDE2LjYyMDg3OTYgNjguMzY3MDAzNCwxNS4yOTUxMTY4IDY3LjMxNjI3MzgsMTQuMzk0OTkzNiBMNTEuNzkxOTE5MiwxLjE2NTI3NTc4IEM1MC4wMjQ0NjY5LC0wLjM0MTkwNzI2MyA0Ny40Mjg5NTYyLC0wLjM0MTkwNzI2MyA0NS42Njg0NjI0LDEuMTU4Mjk4MDggWiIgaWQ9IlNoYXBlIiBmaWxsPSIjM0REQzk3Ij48L3BhdGg+CiAgICAgICAgICAgICAgICA8L2c+CiAgICAgICAgICAgIDwvZz4KICAgICAgICA8L2c+CiAgICA8L2c+Cjwvc3ZnPg==);width:124px;height:84px;margin-bottom:40px}.header h1{color:#0a253a;font-size:24px;font-weight:bold;line-height:24px;margin:0 0 6px 0}.header h2{color:#0a253a;font-size:16px;line-height:21px;margin:0}.info{display:flex;margin-bottom:40px;}.info .column{width:50%}.info .item{display:flex;line-height:35px;}.info .item label{min-width:200px;font-weight:600;text-transform:uppercase;font-size:15px}.info .item span{padding-right:25px}table{width:100%;border-collapse:collapse;text-align:left;border:1px solid #dfe2e4;font-size:14px;}table thead th{border-bottom:2px solid #bfc7cb;background-color:#f2f2f2;color:#555;font-weight:bold;font-size:12px;text-transform:uppercase}table td,table th{margin:0;padding:10px;border-right:1px dotted #9da6ab;}table td:last-child,table th:last-child{border-right:0}table tr:nth-child(even){background-color:#fbfbfb}.table-info{display:flex;flex-direction:column;margin-top:25px;}.table-info .item{justify-content:flex-end;}.table-info .item label,.table-info .item span{font-size:14px}.table-info .item label{min-width:150px}.table-info .item span{padding:0}.signatures{display:flex;width:100%;justify-content:space-between;margin-top:90px;}.signatures .signature-item{width:300px;padding:5px 0;border-top:2px solid #cbcbcb;text-align:center;color:#707779;line-height:21px;}.signatures .signature-item.date{margin-left:50px}.signatures .signature-item.signature{margin-right:50px}.note{width:100%;margin:40px 0;text-align:center}.footer{display:flex;}.footer *{width:33.33%}.footer .phone{text-align:center}.footer .email{text-align:right}\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "<div class=\"container\">\n" +
            "    <div class=\"header\">\n" +
            "        <div class=\"logo\"></div>\n" +
            "        <h1>Statement of Deposit Accounting</h1>\n" +
            "        <h2>(California Civil Code section 1950.5)</h2>\n" +
            "    </div>\n" +
            "\n" +
            "    <div class=\"info\">\n" +
            "        <div class=\"column\">\n" +
            "            <div class=\"item\">\n" +
            "                <label>Statement date</label>\n" +
            "                <span>09/10/2017</span>\n" +
            "            </div>\n" +
            "\n" +
            "            <div class=\"item\">\n" +
            "                <label>Lease signer(s)</label>\n" +
            "                <span>Daniel Sudin, Susan Sudin</span>\n" +
            "            </div>\n" +
            "\n" +
            "            <div class=\"item\">\n" +
            "                <label>Premises</label>\n" +
            "                <span>123 Main Street, Oakland, CA #2</span>\n" +
            "            </div>\n" +
            "\n" +
            "            <div class=\"item\">\n" +
            "                <label>Forwarding address</label>\n" +
            "                <span>116 Telegraph Ave, Apt 2 Oakland, CA 94612</span>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "\n" +
            "        <div class=\"column\">\n" +
            "            <div class=\"item\">\n" +
            "                <label>Statement date</label>\n" +
            "                <span>09/10/2017</span>\n" +
            "            </div>\n" +
            "\n" +
            "            <div class=\"item\">\n" +
            "                <label>Move-in date</label>\n" +
            "                <span>09/08/2017</span>\n" +
            "            </div>\n" +
            "\n" +
            "            <div class=\"item\">\n" +
            "                <label>Notice to vacate date</label>\n" +
            "                <span>08/08/2018</span>\n" +
            "            </div>\n" +
            "\n" +
            "            <div class=\"item\">\n" +
            "                <label>Move-out date</label>\n" +
            "                <span>09/08/2018</span>\n" +
            "            </div>\n" +
            "\n" +
            "            <div class=\"item\">\n" +
            "                <label>Lease Break</label>\n" +
            "                <span>yes</span>\n" +
            "            </div>\n" +
            "\n" +
            "        </div>\n" +
            "\n" +
            "    </div>\n" +
            "\n" +
            "    <table>\n" +
            "        <thead>\n" +
            "        <tr>\n" +
            "            <th>Date</th>\n" +
            "            <th>Description</th>\n" +
            "            <th>Charge</th>\n" +
            "            <th>Receipt</th>\n" +
            "            <th>Balance</th>\n" +
            "        </tr>\n" +
            "        </thead>\n" +
            "        <tbody>\n" +
            "        <tr>\n" +
            "            <td>09/08/2018</td>\n" +
            "            <td>Payment, PayLease</td>\n" +
            "            <td></td>\n" +
            "            <td>$1,000.09</td>\n" +
            "            <td>($2,000.00)</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td>10/08/2018</td>\n" +
            "            <td>Rent May 2017</td>\n" +
            "            <td>$2,400.59</td>\n" +
            "            <td></td>\n" +
            "            <td>$1,000.00</td>\n" +
            "        </tr>\n" +
            "        </tbody>\n" +
            "    </table>\n" +
            "\n" +
            "    <div class=\"table-info info\">\n" +
            "        <div class=\"item\">\n" +
            "            <label>Refund due</label>\n" +
            "            <span>$5,000.09</span>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "\n" +
            "    <div class=\"signatures\">\n" +
            "        <div class=\"signature-item date\">\n" +
            "            <label>Date</label>\n" +
            "        </div>\n" +
            "\n" +
            "        <div class=\"signature-item signature\">\n" +
            "            <label>Mynd Management, Inc.</label>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "\n" +
            "    <div class=\"note\">\n" +
            "        If you did not elect to have your refund sent via ACH, the check will be mailed separate from this statement.\n" +
            "    </div>\n" +
            "\n" +
            "\n" +
            "    <div class=\"footer\">\n" +
            "        <div class=\"address\">P.O. Box 71006 Oakland, CA 94612</div>\n" +
            "        <div class=\"phone\">510-306-4440</div>\n" +
            "        <div class=\"email\">residents@mynd.co</div>\n" +
            "    </div>\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "</body>\n" +
            "</html>\n";

    @Test public void testSomeLibraryMethod() throws ExecutionException, InterruptedException {

        Library classUnderTest = new Library();
        assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
    }



    @Test public void testOkHttp() throws ExecutionException, InterruptedException, IOException {
        String asB64 = Base64.getEncoder().encodeToString(SOURCE.getBytes("utf-8"));
        Data data =  new Data();
        data.setContents(asB64);

        Gson gson = new Gson();
        String json = gson.toJson(data);

        System.out.println(json);

        RequestBody requestBody = RequestBody.create(null, json);


        Request request = new Request.Builder()
                .url("http://192.168.99.100:32768/")
                .header("Content-Type" , "application/json")
                .post(requestBody)
                .build();

        System.out.println(request);


        OkHttpClient client = new OkHttpClient();
//        final InputStream[] inputStream = new InputStream[1];
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(final Request request, final IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(final Response response) throws IOException {
//                inputStream[0] = response.body().byteStream();
//            }
//        });

        Response response = client.newCall(request).execute();
        response.body().byteStream();

        File file = new File("c:\\bubblJWT\\pdf\\okhttp3.pdf");
        FileUtils.writeByteArrayToFile(file, response.body().bytes());

        System.out.println(response);


    }

    public class Data {
        private String contents;

        public String getContents() {
            return contents;
        }

        public void setContents(final String contents) {
            this.contents = contents;
        }
    }


    public static final String SOURCE2 = "<html>\n" +
            "<head>\n" +
            "    <title>Welcome!</title>\n" +
            "    <style>*{font-family:\"Open Sans\",sans-serif}body,html{width:100%;height:100%;margin:0;padding:0;background-color:#fff}body{font-size:16px;color:#666c6f;}body *{box-sizing:border-box}.clearfix:after{display:table;clear:both;content:''}.container{padding:20px}.header{text-align:center;margin-bottom:40px;}.header .logo{display:inline-block;background-image:url(\"data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHN2ZyB3aWR0aD0iMTI0cHgiIGhlaWdodD0iODRweCIgdmlld0JveD0iMCAwIDEyNCA4NCIgdmVyc2lvbj0iMS4xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIj4KICAgIDxkZWZzPjwvZGVmcz4KICAgIDxnIGlkPSJQTS0tLURlcG9zaXQtQWNjb3VudGluZyIgc3Ryb2tlPSJub25lIiBzdHJva2Utd2lkdGg9IjEiIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0iZXZlbm9kZCI+CiAgICAgICAgPGcgaWQ9IkRlcG9zaXQtQWNjb3VudGluZy1QTS0tLTQtU3RhdGVtZW50LVJlZnVuZCIgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoLTc3My4wMDAwMDAsIC0xODQuMDAwMDAwKSIgZmlsbC1ydWxlPSJub256ZXJvIj4KICAgICAgICAgICAgPGcgaWQ9IlBhZ2UtSGVhZGVyIiB0cmFuc2Zvcm09InRyYW5zbGF0ZSgyNjAuMDAwMDAwLCAxODQuMDAwMDAwKSI+CiAgICAgICAgICAgICAgICA8ZyBpZD0iTVlORF9fTG9nbyIgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoNTEzLjAwMDAwMCwgMC4wMDAwMDApIj4KICAgICAgICAgICAgICAgICAgICA8cG9seWdvbiBpZD0iU2hhcGUiIGZpbGw9IiMwMTE2MzgiIHBvaW50cz0iMjIuNzY4MTI1NyA4My43NjAzMDE4IDE3LjkzODk0NSA4My43NjAzMDE4IDE3LjkzODk0NSA3MC40Njc3ODQ4IDEzLjMyNTQ3NyA4My43NjAzMDE4IDkuNjU4MzYxMzkgODMuNzYwMzAxOCA0Ljg5MTgwNjk2IDcwLjQ2Nzc4NDggNC44OTE4MDY5NiA4My43NjAzMDE4IDAuMDYyNjI2MjYyNiA4My43NjAzMDE4IDAuMDYyNjI2MjYyNiA2Mi4zMTc4MzIgNS43NzU1MzMxMSA2Mi4zMTc4MzIgMTEuNDg4NDQgNzcuMjM2MTUzIDE3LjEzODcyMDUgNjIuMzE3ODMyIDIyLjc2MTE2NzIgNjIuMzE3ODMyIDIyLjc2MTE2NzIgODMuNzYwMzAxOCI+PC9wb2x5Z29uPgogICAgICAgICAgICAgICAgICAgIDxwb2x5Z29uIGlkPSJTaGFwZSIgZmlsbD0iIzAxMTYzOCIgcG9pbnRzPSI0Ni4yNTk5MzI3IDgzLjc2MDMwMTggNDAuNzkwNTcyNCA4My43NjAzMDE4IDQ0LjcyOTA2ODUgNzUuNzA4MDM2OSAzNy4xNTgyNDkyIDYyLjMxNzgzMiA0Mi45MzM3ODIzIDYyLjMxNzgzMiA0Ny40ODQ2MjQgNzAuNzM5OTE1IDUxLjM5NTI4NjIgNjIuMzE3ODMyIDU2Ljg2NDY0NjUgNjIuMzE3ODMyIj48L3BvbHlnb24+CiAgICAgICAgICAgICAgICAgICAgPHBvbHlnb24gaWQ9IlNoYXBlIiBmaWxsPSIjMDExNjM4IiBwb2ludHM9IjgzLjk1Mzk4NDMgNzYuMjg3MTg1OSA4My45NTM5ODQzIDYyLjMxNzgzMiA4OC44NzM2MjUxIDYyLjMxNzgzMiA4OC44NzM2MjUxIDgzLjc2MDMwMTggODMuNzEwNDM3NyA4My43NjAzMDE4IDc2LjAxNDM2NTkgNjkuOTc5MzQ1OCA3Ni4wMTQzNjU5IDgzLjc2MDMwMTggNzEuMDY2ODkxMSA4My43NjAzMDE4IDcxLjA2Njg5MTEgNjIuMzE3ODMyIDc2LjIzMDA3ODYgNjIuMzE3ODMyIj48L3BvbHlnb24+CiAgICAgICAgICAgICAgICAgICAgPHBhdGggZD0iTTExMi44MTc3MzMsNjIuMzE3ODMyIEMxMTkuOTAxNDU5LDYyLjMxNzgzMiAxMjMuOTM3Mzc0LDY3LjU1ODA4NDIgMTIzLjkzNzM3NCw3My4wMzU1NzgxIEMxMjMuOTM3Mzc0LDc4LjUyMDA0OTcgMTE5LjkzNjI1MSw4My43NTMzMjQxIDExMi44MTc3MzMsODMuNzUzMzI0MSBMMTA0Ljk2ODU3NSw4My43NTMzMjQxIEwxMDQuOTY4NTc1LDYyLjMxMDg1NDMgTDExMi44MTc3MzMsNjIuMzEwODU0MyBMMTEyLjgxNzczMyw2Mi4zMTc4MzIgWiBNMTA5LjkxNjA0OSw2Ni42MDkxMTcxIEwxMDkuOTE2MDQ5LDc5LjQ3NTk5NDUgTDExMi41NzQxODYsNzkuNDc1OTk0NSBDMTE2LjU3NTMwOSw3OS40NzU5OTQ1IDExOC44OTk0MzksNzYuMzQ5OTg1MiAxMTguODk5NDM5LDczLjA0MjU1NTggQzExOC44OTk0MzksNjkuNzM1MTI2MyAxMTYuNTc1MzA5LDY2LjYwOTExNzEgMTEyLjU3NDE4Niw2Ni42MDkxMTcxIEwxMDkuOTE2MDQ5LDY2LjYwOTExNzEgWiIgaWQ9IlNoYXBlIiBmaWxsPSIjMDExNjM4Ij48L3BhdGg+CiAgICAgICAgICAgICAgICAgICAgPHBhdGggZD0iTTcyLjQ4NjQxOTgsMS4yMTQxMTk2NyBMNTkuNTY0NTM0MiwxMi40MDYzNDkzIEM1Ny4wNTk0ODM3LDE0LjU3NjQxMzcgNTUuNjI2MDM4MiwxNy43MjMzNTYxIDU1LjYyNjAzODIsMjEuMDQ0NzQwOSBMNTUuNjI2MDM4Miw0Mi4yNjM5MjQ0IEM1NS42MjYwMzgyLDQ0LjUyNDY5ODkgNTcuNDQ5MTU4Miw0Ni4zNTI4NTYxIDU5LjcwMzcwMzcsNDYuMzUyODU2MSBMNjQuODQ2MDE1Nyw0Ni4zNTI4NTYxIEM2Ny4xMDA1NjEyLDQ2LjM1Mjg1NjEgNjguOTIzNjgxMyw0NC41MjQ2OTg5IDY4LjkyMzY4MTMsNDIuMjYzOTI0NCBMNjguOTIzNjgxMywzMy4wNjAzMzkxIEM2OC45MjM2ODEzLDI5LjM3NjExMzkgNzEuOTAxOTA4LDI2LjM5NjYzNjMgNzUuNTY5MDIzNiwyNi4zOTY2MzYzIEw3NS43MTUxNTE1LDI2LjM5NjYzNjMgQzc5LjM4OTIyNTYsMjYuMzk2NjM2MyA4Mi4zNjA0OTM4LDI5LjM4MzA5MTYgODIuMzYwNDkzOCwzMy4wNjAzMzkxIEw4Mi4zNjA0OTM4LDQyLjI1Njk0NjcgQzgyLjM2MDQ5MzgsNDQuNTE3NzIxMiA4NC4xODM2MTM5LDQ2LjM0NTg3ODQgODYuNDM4MTU5NCw0Ni4zNDU4Nzg0IEw5MS43MjY1OTkzLDQ2LjM0NTg3ODQgQzkzLjk4MTE0NDgsNDYuMzQ1ODc4NCA5NS44MDQyNjQ5LDQ0LjUxNzcyMTIgOTUuODA0MjY0OSw0Mi4yNTY5NDY3IEw5NS44MDQyNjQ5LDE4LjAwOTQ0MTggQzk1LjgwNDI2NDksMTYuNjIwODc5NiA5NS4xOTE5MTkyLDE1LjI5NTExNjggOTQuMTQxMTg5NywxNC4zOTQ5OTM2IEw3OC42Mzc3MTA0LDEuMTg2MjA4ODcgQzc2Ljg2MzI5OTcsLTAuMzI3OTUxODY1IDc0LjI1Mzg3MjEsLTAuMzEzOTk2NDY2IDcyLjQ4NjQxOTgsMS4yMTQxMTk2NyBaIiBpZD0iU2hhcGUiIGZpbGw9IiMzNUJBN0QiPjwvcGF0aD4KICAgICAgICAgICAgICAgICAgICA8cGF0aCBkPSJNNDUuNjY4NDYyNCwxLjE1ODI5ODA4IEwzMC4xMDIzNTY5LDE0LjM5NDk5MzYgQzI5LjA0NDY2ODksMTUuMjk1MTE2OCAyOC40MzIzMjMyLDE2LjYyMDg3OTYgMjguNDMyMzIzMiwxOC4wMDk0NDE4IEwyOC40MzIzMjMyLDQyLjI0OTk2OSBDMjguNDMyMzIzMiw0NC41MTA3NDM1IDMwLjI1NTQ0MzMsNDYuMzM4OTAwNyAzMi41MDk5ODg4LDQ2LjMzODkwMDcgTDM4LjAyMTA5OTksNDYuMzM4OTAwNyBDNDAuMjc1NjQ1Myw0Ni4zMzg5MDA3IDQyLjA5ODc2NTQsNDQuNTEwNzQzNSA0Mi4wOTg3NjU0LDQyLjI0OTk2OSBMNDIuMDk4NzY1NCwzMy4wNjAzMzkxIEM0Mi4wOTg3NjU0LDI5LjM3NjExMzkgNDUuMDc2OTkyMSwyNi4zOTY2MzYzIDQ4Ljc0NDEwNzcsMjYuMzk2NjM2MyBMNDguODkwMjM1NywyNi4zOTY2MzYzIEM1Mi41NjQzMDk4LDI2LjM5NjYzNjMgNTUuNTM1NTc4LDI5LjM4MzA5MTYgNTUuNTM1NTc4LDMzLjA2MDMzOTEgTDU1LjUzNTU3OCw0Mi4yNTY5NDY3IEM1NS41MzU1NzgsNDQuNTE3NzIxMiA1Ny4zNTg2OTgxLDQ2LjM0NTg3ODQgNTkuNjEzMjQzNSw0Ni4zNDU4Nzg0IEw2NC45MDE2ODM1LDQ2LjM0NTg3ODQgQzY3LjE1NjIyOSw0Ni4zNDU4Nzg0IDY4Ljk3OTM0OSw0NC41MTc3MjEyIDY4Ljk3OTM0OSw0Mi4yNTY5NDY3IEw2OC45NzkzNDksMTguMDA5NDQxOCBDNjguOTc5MzQ5LDE2LjYyMDg3OTYgNjguMzY3MDAzNCwxNS4yOTUxMTY4IDY3LjMxNjI3MzgsMTQuMzk0OTkzNiBMNTEuNzkxOTE5MiwxLjE2NTI3NTc4IEM1MC4wMjQ0NjY5LC0wLjM0MTkwNzI2MyA0Ny40Mjg5NTYyLC0wLjM0MTkwNzI2MyA0NS42Njg0NjI0LDEuMTU4Mjk4MDggWiIgaWQ9IlNoYXBlIiBmaWxsPSIjM0REQzk3Ij48L3BhdGg+CiAgICAgICAgICAgICAgICA8L2c+CiAgICAgICAgICAgIDwvZz4KICAgICAgICA8L2c+CiAgICA8L2c+Cjwvc3ZnPg==\");width:124px;height:84px;margin-bottom:40px}.header h1{color:#0a253a;font-size:24px;font-weight:bold;line-height:24px;margin:0 0 6px 0}.header h2{color:#0a253a;font-size:16px;line-height:21px;margin:0}.info{margin-bottom:40px;}.info .column{float:left;width:50%}.info .item{line-height:35px;}.info .item label,.info .item span{float:left;width:50%}.info .item label{min-width:200px;font-weight:600;text-transform:uppercase;font-size:15px}.info .item span{padding-right:25px}table{width:100%;border-collapse:collapse;text-align:left;border:1px solid #dfe2e4;font-size:14px;}table thead th{border-bottom:2px solid #bfc7cb;background-color:#f2f2f2;color:#555;font-weight:bold;font-size:12px;text-transform:uppercase}table td,table th{margin:0;padding:10px;border-right:1px dotted #9da6ab;}table td:last-child,table th:last-child{border-right:0}table tr:nth-child(even){background-color:#fbfbfb}.table-info{margin-top:25px;}.table-info .item{text-align:right;}.table-info .item label,.table-info .item span{float:none;min-width:0;font-size:14px}.table-info .item label{min-width:150px}.table-info .item span{padding:0}.signatures{margin-top:90px;}.signatures .signature-item{width:300px;padding:5px 0;border-top:2px solid #cbcbcb;text-align:center;color:#707779;line-height:21px;}.signatures .signature-item.date{float:left;margin-left:50px}.signatures .signature-item.signature{float:right;margin-right:50px}.note{width:100%;margin:40px 0;text-align:center}.footer *{float:left;width:33.33%}.footer .phone{text-align:center}.footer .email{text-align:right}</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "  <div class=\"container\">\n" +
            "    <div class=\"header\">\n" +
            "      <div class=\"logo\"></div>\n" +
            "      <h1>Statement of Deposit Accounting</h1>\n" +
            "      <h2>(California Civil Code section 1950.5)</h2>\n" +
            "    </div>\n" +
            "\n" +
            "    <div class=\"info clearfix\">\n" +
            "      <div class=\"column clearfix\">\n" +
            "        <div class=\"item\">\n" +
            "          <label>Lease signer(s)</label>\n" +
            "          <span>Daniel Sudin, Susan Sudin</span>\n" +
            "        </div>\n" +
            "\n" +
            "        <div class=\"item\">\n" +
            "          <label>Premises</label>\n" +
            "          <span>123 Main Street, Oakland, CA #2</span>\n" +
            "        </div>\n" +
            "\n" +
            "        <div class=\"item\">\n" +
            "          <label>Forwarding address</label>\n" +
            "          <span>116 Telegraph Ave, Apt 2 Oakland, CA 94612</span>\n" +
            "        </div>\n" +
            "      </div>\n" +
            "\n" +
            "      <div class=\"column clearfix\">\n" +
            "        <div class=\"item\">\n" +
            "          <label>Move-in date</label>\n" +
            "          <span>09/08/2017</span>\n" +
            "        </div>\n" +
            "\n" +
            "        <div class=\"item\">\n" +
            "          <label>Notice to vacate date</label>\n" +
            "          <span>08/08/2018</span>\n" +
            "        </div>\n" +
            "\n" +
            "        <div class=\"item\">\n" +
            "          <label>Move-out date</label>\n" +
            "          <span>09/08/2018</span>\n" +
            "        </div>\n" +
            "\n" +
            "        <div class=\"item\">\n" +
            "          <label>Lease Break</label>\n" +
            "          <span>yes</span>\n" +
            "        </div>\n" +
            "\n" +
            "      </div>\n" +
            "    </div>\n" +
            "\n" +
            "    <table>\n" +
            "      <thead>\n" +
            "        <tr>\n" +
            "          <th>Date</th>\n" +
            "          <th>Description</th>\n" +
            "          <th>Charge</th>\n" +
            "          <th>Receipt</th>\n" +
            "          <th>Balance</th>\n" +
            "        </tr>\n" +
            "      </thead>\n" +
            "      <tbody>\n" +
            "        <tr>\n" +
            "          <td>09/08/2018</td>\n" +
            "          <td>Payment, PayLease</td>\n" +
            "          <td></td>\n" +
            "          <td>$1,000.09</td>\n" +
            "          <td>($2,000.00)</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "          <td>10/08/2018</td>\n" +
            "          <td>Rent May 2017</td>\n" +
            "          <td>$2,400.59</td>\n" +
            "          <td></td>\n" +
            "          <td>$1,000.00</td>\n" +
            "        </tr>\n" +
            "      </tbody>\n" +
            "    </table>\n" +
            "\n" +
            "    <div class=\"table-info info\">\n" +
            "      <div class=\"item\">\n" +
            "        <label>Refund due</label>\n" +
            "        <span>$5,000.09</span>\n" +
            "      </div>\n" +
            "    </div>\n" +
            "\n" +
            "    <div class=\"signatures clearfix\">\n" +
            "      <div class=\"signature-item date\">\n" +
            "        <label>Date</label>\n" +
            "      </div>\n" +
            "\n" +
            "      <div class=\"signature-item signature\">\n" +
            "        <label>Mynd Management, Inc.</label>\n" +
            "      </div>\n" +
            "    </div>\n" +
            "\n" +
            "    <div class=\"note\">\n" +
            "      If you did not elect to have your refund sent via ACH, the check will be mailed separate from this statement.\n" +
            "    </div>\n" +
            "\n" +
            "\n" +
            "    <div class=\"footer clearfix\">\n" +
            "      <div class=\"address\">P.O. Box 71006 Oakland, CA 94612</div>\n" +
            "      <div class=\"phone\">510-306-4440</div>\n" +
            "      <div class=\"email\">residents@mynd.co</div>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "</body>\n" +
            "</html>";



    @Test public void  testPdf () throws IOException, InterruptedException {
        Pdf pdf = new Pdf();

//        pdf.addPage("<html><head><meta charset=\"utf-8\"></head><h1>Müller</h1></html>", PageType.htmlAsString);
//        pdf.addPage("http://www.google.com", PageType.url);

//        pdf.addPageFromUrl("http://localhost:63343/untitled/src/js/soda.html?_ijt=7r8fl5cajuk05un203mkauoq6k");
//        pdf.addPageFromUrl("http://google.com");

//        pdf.addPageFromFile("C:\\Users\\one\\WebstormProjects\\untitled\\src\\js\\soda.html");
//
        pdf.addPageFromString(SOURCE2);

//        pdf.addParam(new Param("-O"), new Param("Landscape"));
// Add a Table of contents
//        pdf.addToc();

// The `wkhtmltopdf` shell command accepts different types of options such as global, page, headers and footers, and toc. Please see `wkhtmltopdf -H` for a full explanation.
// All options are passed as array, for example:
//        pdf.addParam(new Param("--no-footer-line"), new Param("--header-html", "file:///header.html"));
//        pdf.addParam(new Param("--enable-javascript"));
//        pdf.getPDF();

// Save the PDF
        pdf.saveAs("d:\\output2222.pdf");
    }
}
