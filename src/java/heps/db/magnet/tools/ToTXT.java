/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author qiaoys
 */
public class ToTXT {

    public  String writeTxt(String dfname, String content) {
        try {
            File writename = new File(dfname);
            writename.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write(content);
            out.flush();
            out.close();

        } catch (IOException e) {
            return "导出失败：" + e;
        }
        return "导出成功";
    }
}
