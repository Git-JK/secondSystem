import pymysql

if __name__ == '__main__':
    db = pymysql.connect(
        host="43.143.170.221",
        port=3306,
        user="miaosha",
        password="jinke200098cs",
        database="miaosha"
    )
    cursor = db.cursor()
    label_list = ["博雅一苑", "博雅二苑", "博雅三苑",  "博雅四苑", "英杰一苑", "英杰二苑", "英杰三苑", "英杰四苑", "未名一苑", "未名二苑", "未名三苑", "未名四苑", "求知一苑", "求知二苑", "求知三苑", "求知四苑"]
    for label in label_list:
        name = label
        description = "班级"
        sql = "INSERT INTO label(name, description) VALUES ('%s', '%s')" %(name, description)
        cursor.execute(sql)
        db.commit()