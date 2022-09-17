package com.shiguang;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ReUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: ShiGuang
 * @create: 2022-09-17 10:57
 * @description:
 */
public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("Content-Type", "application/json");

        while (true) {
            String result = HttpUtil.post("https://kapi.10010.com/kcardorder/optimizationNumber/recommend?productType=k107&provinceCode=19&cityCode=196&guessValue=all", paramMap);
            JSONObject entries = JSONUtil.parseObj(result);
            Object data = JSONUtil.getByPath(entries, "data");
            JSONArray jsonObject = JSONUtil.parseArray(data);
            List<Dict> dicts = JSONUtil.toList(jsonObject, Dict.class);

            for (int i = 0; i < dicts.size(); i++) {
                String number = dicts.get(i).getStr("number");
                // 排除带4的号码
                if (ReUtil.isMatch(Patterns.NO4, number)) {
                    System.out.println(number);
                    // 3A及以上
                    process(Patterns.AAA, "AAA", number);
                    // 尾号AABB类型
                    process(Patterns.AABB, "AABB", number);
                    // 4连号
                    process(Patterns.ABCD, "ABCD", number);
                    // 以520或者521结尾的
                    process(Patterns.LOVE, "LOVE", number);
                    // 尾号ABCDABCD
                    process(Patterns.ABCDABCD, "ABCDABCD", number);
                    // 尾号ABCDDCBA
                    process(Patterns.ABCDDCBA, "ABCDDCBA", number);
                    // AABBCC
                    process(Patterns.AABBCC, "AABBCC", number);
                    // ABCDE
                    process(Patterns.ABCDE, "ABCDE", number);
                    // 同一个数字至少出现5次
                    process(Patterns.ONE5S, "ONE5S", number);
                }
            }
            System.out.println("--------------------");
            Thread.sleep(10000);
        }

    }

    /**
     * 符合条件的入库
     *
     * @param p
     * @param type
     * @param number
     * @throws SQLException
     */
    private static void process(Pattern p, String type, String number) throws SQLException {
        Matcher matcher = p.matcher(number);
        if (matcher.find()) {
            Db.use().insert(
                    Entity.create("number")
                            .set("num", number)
                            .set("type", type)
            );
        }
    }
}