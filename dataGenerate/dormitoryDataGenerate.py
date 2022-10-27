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
    room_id_list = ['111', '112', '121', '122', '131', '132', '141', '142', '151', '152', '211', '212', '221', '222', '231', '232', '241', '242', '251', '252', '311', '312', '321', '322', '331', '332', '341', '342', '351', '352']
    room_capacity = [2, 3, 4, 5, 6]
    building_id_list = [8, 9, 13, 14, 5]
    for i in range(5):
        user_gender = ''
        if i < 3:
            user_gender = '1'
        else:
            user_gender = '2'
        building_id = building_id_list[i]
        for room_id in room_id_list:
            true_room_id = int(str(building_id) + room_id)
            bed_count_all = random.choice(room_capacity)
            bed_count_available = bed_count_all
            bed_count_free = bed_count_all
            is_empty = "1"
            sql = "INSERT INTO dormitory(room_id, building_id, bed_count_all, bed_count_available, user_gender, bed_count_free, is_empty, room_member_id_list) VALUES (%d, %d, %d, %d, %s, %d, %s, null)" %(true_room_id, building_id, bed_count_all, bed_count_available, user_gender, bed_count_free, is_empty)
            cursor.execute(sql)
            db.commit()
        