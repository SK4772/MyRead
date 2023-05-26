import $ from 'jquery'
import CryptoJS from '../assets/CryptoJS'

var appKey = '717f147fdc2f0ef6';
var key = 'JmTNzdviEw31ReSWFAxs0masMzJqzdn6'; //注意：暴露appSecret，有被盗用造成损失的风险
var from = 'en';
var to = 'en';
var vocabId = ''; //您的用户词表ID

// 多个query可以用\n连接  如 query='apple\norange\nbanana\npear'
function getMp3Url(query) {
    let salt = (new Date).getTime();
    let curtime = Math.round(new Date().getTime() / 1000);
    var str1 = appKey + truncate(query) + salt + curtime + key;
    var sign = CryptoJS.SHA256(str1).toString(CryptoJS.enc.Hex);
    return new Promise((resolve, reject) => {
        $.ajax({
            url: 'https://openapi.youdao.com/api',
            type: 'post',
            dataType: 'jsonp',
            data: {
                q: query,
                appKey: appKey,
                salt: salt,
                from: from,
                to: to,
                sign: sign,
                signType: "v3",
                curtime: curtime,
                vocabId: vocabId,
                voice: '0'
            },
            success: function(data) {
                resolve(data)
            },
            fail(err) {
                reject(err)
            }
        });
    })
}

function truncate(q) {
    var len = q.length;
    if (len <= 20) return q;
    return q.substring(0, 10) + len + q.substring(len - 10, len);
}

export default {getMp3Url}