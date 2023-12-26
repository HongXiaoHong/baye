# pip install selenium

# selenium 4
import logging
import time

import requests
from selenium import webdriver
from selenium.webdriver.chrome.service import Service as ChromeService
from selenium.webdriver.common.by import By

SAVE_MUSIC_PATH = "E:/BaiduSyncdisk/music/download/temp"


def save_audio(url, file_path, song_info):
    """
    通过请求url保存音乐文件到本地
    :param song_info: 需要保存的歌曲信息
    :param url: 音乐请求url
    :param file_path: 音乐保存路径
    """
    headers = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.182 Safari/537.36"
        # 添加其他请求头字段，根据需要自行添加
    }
    response = requests.get(url, headers=headers, stream=True)

    if response.status_code == 200:
        content_type = response.headers.get('Content-Type')
        file_extension = '.mp3'
        if content_type == 'audio/x-m4a' or content_type == "audio/mp4":
            file_extension = '.m4a'

        file_path_with_extension = file_path + file_extension

        with open(file_path_with_extension, 'wb') as file:
            for chunk in response.iter_content(chunk_size=8192):
                file.write(chunk)
        print('File saved successfully:', file_path_with_extension)
    else:
        print('Failed to download file:', response.status_code, song_info)


def dowload_music_magnetic_field_music():
    search_page_driver = webdriver.Chrome(service=ChromeService("D:/app/code/chromedriver-win64/chromedriver.exe"))
    search_page_driver.get("https://www.hifini.com/forum-1.htm")
    assert "音乐" in search_page_driver.title
    search_a_elements = search_page_driver.find_elements(By.CSS_SELECTOR,
                                                         '#body > div > div > div.col-lg-9.main > div > div.card-body > ul > li > div > div.subject.break-all > a')
    music_urls = []
    for a in search_a_elements:
        if "积分" in a.get_attribute("innerText") or "新人" in a.get_attribute("innerText") or "链接" in a.get_attribute(
                "innerText"):
            continue
        print(a.get_attribute("href"), a.get_attribute("innerText"))
        music_urls.append(a.get_attribute("href"))
    print(f"music_urls:{music_urls}, music_urls length: {len(music_urls)}")
    for music_url in music_urls:
        try:
            search_page_driver.get(music_url)
            song_name = search_page_driver.execute_script("return `${ap4.music.title}-${ap4.music.author}`;")
            print("song_name", song_name)
            audio_src = search_page_driver.execute_script("return ap4.audio.src;")

            file_path = f'{SAVE_MUSIC_PATH}/{song_name}'
            save_audio(audio_src, file_path, {"song_name": song_name})
        except Exception as e:
            logging.error(f"发生异常啦!", e)
        time.sleep(10)
    time.sleep(5)
    search_page_driver.close()

# search_page_driver = webdriver.Chrome(service=ChromeService(ChromeDriverManager().install()))
