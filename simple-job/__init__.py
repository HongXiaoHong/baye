# pip install apscheduler

import logging

from apscheduler.schedulers.background import BlockingScheduler
from apscheduler.triggers.cron import CronTrigger

from music_magnetic_field_list import dowload_music_magnetic_field_music

scheduler = BlockingScheduler()

scheduler.add_job(dowload_music_magnetic_field_music, CronTrigger.from_crontab('*/10 * * * *'))

# def my_clock():
#     print(f"hello! now is {datetime.now()}")
#
#
# scheduler.add_job(dowload_music_magnetic_field_music, "interval", minutes=3)
# scheduler.add_job(my_clock, "interval", minutes=3)
logging.basicConfig()
logging.getLogger('apscheduler').setLevel(logging.DEBUG)
scheduler.start()
