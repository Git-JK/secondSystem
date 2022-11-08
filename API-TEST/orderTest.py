from email import header
import json
import random
from wsgiref import headers

from locust import HttpUser, TaskSet, task

class ScriptTasks(TaskSet):
    @task
    def submitOrder(self):
        data = {
            "buildingId": random.choice([8, 9, 13, 14, 5]),
            "applyTime": "2022-10-24 22:00:00",
            "applyMemberNumber": 1,
            "applyMemberIdList": str(random.randint(2201210200, 2201210800)),
            "applyMemberCodeList": "072534",
            "gender": str(random.randint(1, 2))
        }
        header = {
            "Content": "application/json;charset=UTF-8",
            "SESSION": "YzRmNDg3ZTAtZjI1NC00YTdlLTg2YmItZjJlYjAwZmU5ZTE5",
            "Cookie": "token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdHVkZW50SWQiOiIyMjAxMjEwMjAwIiwiaXNzIjoiYXV0aDAiLCJuYW1lIjoiam9sbHlfdHVyaW5nIiwiZXhwIjoxNjY3NzM2NjE1fQ.C-pxMfl5pq9HBkUKKrKAiuLEPb_rn3R68rKnDgzR27I"
        }
        response = self.client.post("/order/putOrder", json=data, headers=header)
        print(response.text)

class UserBehavior(TaskSet):
    tasks = {ScriptTasks: 15}
    
class WebsiteUser(HttpUser):
    host = "http://127.0.0.1:8899"
    tasks = [UserBehavior]
    min_wait = 3000  # 单位为毫秒
    max_wait = 6000  # 单位为毫秒
    
if __name__ == "__main__":
    import os
    
    os.system("locust -f orderTest.py --host=http://127.0.0.1:8899")