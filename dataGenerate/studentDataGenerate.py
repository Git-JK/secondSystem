import pymysql
import namesgenerator
import random

if __name__ == "__main__":
    db = pymysql.connect(
        host="43.143.170.221",
        port=3306,
        user="miaosha",
        password="jinke200098cs",
        database="miaosha"
    )
    cursor = db.cursor()
    label_list = ["博雅一苑", "博雅二苑", "博雅三苑",  "博雅四苑", "英杰一苑", "英杰二苑", "英杰三苑", "英杰四苑", "未名一苑", "未名二苑", "未名三苑", "未名四苑", "求知一苑", "求知二苑", "求知三苑", "求知四苑"]
    for i in range(2201210200, 2201210600):
        student_id = i
        name = namesgenerator.get_random_name()
        gender = str(random.randint(1, 2))
        phone_number = str(random.randint(13000000000, 19000000000))
        label = random.choice(label_list)
        status = "0"
        password = "123456"
        has_bedroom = "0"
        bedroom_id = None
        verification_code = "072534"
        user_type = "3"
        sql = "INSERT INTO student(student_id, name, gender, phone_number, status, label, password, has_bedroom, bedroom_id, verification_code, user_type) VALUES (%d, '%s', '%s', '%s', '%s', '%s', '%s', '%s', null, '%s', '%s')" % (student_id, name, gender, phone_number, status, label, password, has_bedroom, verification_code, user_type)
        cursor.execute(sql)
        db.commit()
        data = cursor.fetchall()
    # data = cursor.fetchall()
    # print(data)