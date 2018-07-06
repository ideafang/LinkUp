package Linkup;

/* 图片的状态封装类 */
/* 和MapPanel中的initBoard()初始化棋盘方法关联
 *  */
public class Chess {
	private int status; //图片的选择 1-20， 0为已消除状态
	 public Chess(int s){
		 this.status = s;
	 }
	 
	 public int getStatus(){
		 return status;
	 }
	 
	 public void setStatus(int s){
		 this.status = s;
	 }
		
}
