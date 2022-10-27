import pymysql
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
    building_id_list = [8, 9, 13, 14, 5]
    gender_list = ['1', '1', '1', '2', '2']
    for i in range(5):
        building_id = building_id_list[i]
        user_gender = gender_list[i]
        search_sql = "SELECT SUM(bed_count_all) FROM dormitory WHERE building_id=%d" %(building_id)
        cursor.execute(search_sql)
        bed_count_all = int(cursor.fetchall()[0][0])
        search_sql = "SELECT COUNT(*) FROM dormitory WHERE building_id=%d" %(building_id)
        cursor.execute(search_sql)
        room_count_all = int(cursor.fetchall()[0][0])
        insert_sql = "INSERT INTO building(building_id, user_gender, room_count_all, bed_count_all, room_count_free, bed_count_free) VALUES (%d, %s, %d, %d, %d, %d)" %(building_id, user_gender, room_count_all, bed_count_all, room_count_all, bed_count_all)
        cursor.execute(insert_sql)
        db.commit()