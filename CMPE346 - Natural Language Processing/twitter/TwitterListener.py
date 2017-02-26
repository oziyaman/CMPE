from tweepy import Stream
import tweepy
from tweepy import OAuthHandler

consumer_key = 'CONSUMER-KEY'
consumer_secret = 'CONSUMER-SECRET'
access_token = 'ACCESS-TOKEN'
access_secret = 'ACCESS-SECRET'

auth = OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_secret)

# Collects tweets which includes oscars hashtag
class TweetListener(tweepy.StreamListener):

    def on_data(self, data):
        try:
            with open('oscars.json', 'a') as f:
                f.write(data)
                return True
        except BaseException as e:
            print("Error on_data: %s" % str(e))
        return True

    def on_error(self, status):
        print(status)
        return True

    def on_exception(self, e):
        print(str(e))
        return True

twitter_stream = Stream(auth, TweetListener())
twitter_stream.filter(track=['oscars'])
