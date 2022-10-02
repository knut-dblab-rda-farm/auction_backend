package org.dblab.auction_backend.service;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WithdrawalMember {
    String WITHDRAWAL_MEMBER_FILE_PATH= "/home/webapp_farm_auction/rda_farm/auction_backend/src/main/resources/static/withdrawal_member.txt";
    String FixedwithdrawalMember = "withdrawal_member_";   // 17
    String withdrawalMember;
    Integer number;
    private Logger log = LoggerFactory.getLogger(WithdrawalMember.class);
    
    public WithdrawalMember(){
        try {
            // 바이트 단위로 파일읽기
            FileInputStream fileStream = null; 
            fileStream = new FileInputStream( WITHDRAWAL_MEMBER_FILE_PATH );
            byte[ ] readBuffer = new byte[fileStream.available()];

            while (fileStream.read( readBuffer ) != -1){}
            withdrawalMember = new String(readBuffer);
            fileStream.close(); //스트림 닫기
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public String getWithdrawalMember(){
        return withdrawalMember;
    }

    public void setWithdrawalMember() {
        BufferedOutputStream bs = null;
        try {
            number = Integer.parseInt(withdrawalMember.substring(18, withdrawalMember.length()));
            number++;
            bs = new BufferedOutputStream(new FileOutputStream(WITHDRAWAL_MEMBER_FILE_PATH));
            withdrawalMember = FixedwithdrawalMember + number;                                      // 다음에 삭제될 탈퇴 회원
            bs.write(withdrawalMember.getBytes());
            bs.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
