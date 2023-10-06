import {get} from"@/common/api.service"
export function getById(id) {
    return get('products/' + id);
}
export function getAll(){
        return get('products');
}
export function getSellerAllProducts(seller){
    return get('products/company/'+ seller)
}
