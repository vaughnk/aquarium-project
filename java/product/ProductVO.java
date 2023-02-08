package product;

import common.Paging;

public class ProductVO extends Paging {

   private int productNum;         // PK
   private String productStatus;
   private String productCategory;
   private String productName;
   private int productPrice;
   private int productCnt;
   private String productInfo;
   private String productInfoImg;
   private String productImg;
   private int productSale;      // 할인율
   private int sellCnt;
   private int pageCnt;

   public int getProductNum() {
      return productNum;
   }


   public void setProductNum(int productNum) {
      this.productNum = productNum;
   }


   public String getProductStatus() {
      return productStatus;
   }


   public void setProductStatus(String productStatus) {
      this.productStatus = productStatus;
   }


   public String getProductCategory() {
      return productCategory;
   }


   public void setProductCategory(String productCategory) {
      this.productCategory = productCategory;
   }


   public String getProductName() {
      return productName;
   }


   public void setProductName(String productName) {
      this.productName = productName;
   }

   public int getProductPrice() {
      return productPrice;
   }


   public void setProductPrice(int productPrice) {
      this.productPrice = productPrice;
   }


   public int getProductCnt() {
      return productCnt;
   }


   public void setProductCnt(int productCnt) {
      this.productCnt = productCnt;
   }


   public String getProductInfo() {
      return productInfo;
   }


   public void setProductInfo(String productInfo) {
      this.productInfo = productInfo;
   }


   public String getProductInfoImg() {
      return productInfoImg;
   }


   public void setProductInfoImg(String productInfoImg) {
      this.productInfoImg = productInfoImg;
   }


   public String getProductImg() {
      return productImg;
   }


   public void setProductImg(String productImg) {
      this.productImg = productImg;
   }


   public int getProductSale() {
      return productSale;
   }


   public void setProductSale(int productSale) {
      this.productSale = productSale;
   }


   public int getSellCnt() {
      return sellCnt;
   }


   public void setSellCnt(int sellCnt) {
      this.sellCnt = sellCnt;
   }


   public int getPageCnt() {
      return pageCnt;
   }


   public void setPageCnt(int pageCnt) {
      this.pageCnt = pageCnt;
   }


   @Override
   public String toString() {
      return "ProductVO [productNum=" + productNum + ", productCategory=" + productCategory + ", productName="
            + productName + ", productPrice=" + productPrice + ", productCnt=" + productCnt + ", productInfo="
            + productInfo + ", productInfoImg=" + productInfoImg + ", productImg=" + productImg + ", productSale="
            + productSale + ", sellCnt=" + sellCnt + ", pageCnt=" + pageCnt + "]";
   }

}