## number_scan

号码扫描入库

## 配置

使用的MySQL57 ，`resources` 下的 `db.setting` 修改数据库连接

## 创建表SQL

```sql
CREATE TABLE `number` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `num` char(11) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号码',
  `type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '号码类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
```

