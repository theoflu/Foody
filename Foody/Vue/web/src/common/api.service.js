import axios from "axios";

export function get(path=''){
        axios.get(path).catch(err=> {
            throw new Error("Http Get Error : api.service"+ err);
        })
}
export function post(path='',params){
    axios.post(path,params).catch(err=> {
        throw new Error("Http Post Error : api.service "+err);
    })
}