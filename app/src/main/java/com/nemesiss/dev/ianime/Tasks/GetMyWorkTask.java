package com.nemesiss.dev.ianime.Tasks;

//public class GetMyWorkTask extends CustomPostExecuteAsyncTask<GetMyWorkRequestInfo, Void, List<QQmusicSearchSongsResponse>> {
//    private OkHttpClient okHttpClient;
//    List<QQmusicSearchSongsResponse> qQmusicSearchSongsResponses =new ArrayList<>();
//
//    public GetMyWorkTask(TaskPostExecuteWrapper<List<QQmusicSearchSongsResponse>> DoInPostExecute) {
//        super(DoInPostExecute);
//    }
//
//    @Override
//    protected List<QQmusicSearchSongsResponse> doInBackground(String... IDs) {
//        try {
//            String VariedUrl = APIDocs.fullQQmusicSearchSongs;
//            VariedUrl = VariedUrl + IDs[0] + "&page=" + 10 + "&zhida=false&perpage=" + 50;
//            return parseJSONWithJSONObject(GetRequest(VariedUrl));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        okHttpClient = new OkHttpClient.Builder().connectTimeout(4500, TimeUnit.MILLISECONDS).build();
//    }
//
//    public List<QQmusicSearchSongsResponse> parseJSONWithJSONObject(String jsonData) {
//        try {
//            JSONObject jsonObject = new JSONObject(jsonData);
//            JSONObject data = jsonObject.getJSONObject("data");
//            JSONObject song = data.getJSONObject("song");
//            JSONArray list = song.getJSONArray("list");
//            int listLength = list.length();
//
//            for (int i = 0; i < listLength; i++) {
//                JSONObject eachSong = list.getJSONObject(i);
//                String albummid=eachSong.getString("albummid");
//                String songmid = eachSong.getString("songmid");
//                String songid=eachSong.getString("songid");
//                String songname = eachSong.getString("songname");
//                JSONArray singer=eachSong.getJSONArray("singer");
//                String singerName=singer.getJSONObject(0).getString("name");
//                QQmusicSearchSongsResponse qQmusicSearchSongsResponse=new QQmusicSearchSongsResponse(songname,singerName,songmid,songid,albummid);
//                qQmusicSearchSongsResponses.add(qQmusicSearchSongsResponse);
//            }
//            return qQmusicSearchSongsResponses;
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
