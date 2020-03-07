package com.dj.bank.util;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.InputStream;

/**
 * ��ţ���ļ���������
 *
 * @author zhangzhikai
 * @date 2019��12��9��
 */
public class QiniuUtils {

	/**
	 * ��ԿAK
	 */
    private static final String ACCESSKEY = "tZkGZ0aN695t6-5Pyj2GtbSxFdyz_XsOe1mRZK8j";

	/**
	 * ��ԿSK
	 */
	private static final String SECRETKEY = "hZRKnH_Mk7OLfDCRuu-I8Gt62oIJmJH2GoEbBAiO";

	/**
	 * �洢�ռ�����
	 */
    private static final String BUCKET = "zzk";

	/**
	 * ��������
	 */
	public static final String URL = "http://q6gw2qec5.bkt.clouddn.com/";

	/**
	 * ����һ����ָ�� Region �����������
	 */
    private static Configuration cfg = new Configuration(Region.region1());

	/**
	 * �����ļ��ϴ�������
	 */
    private static UploadManager uploadManager = new UploadManager(cfg);
    /**
     * �����ϴ�����
     */
    private static Auth auth = Auth.create(ACCESSKEY, SECRETKEY);
    private static String upToken = auth.uploadToken(BUCKET);

    /**
     * �����ļ��ϴ�
     * @param fileName �ļ���
     */
    public static void upload(String fileName) {
        try {
            //�����Windows����£���ʽ�� D:\\qiniu\\test.png
            String localFilePath = "E:\\upload\\" + fileName;
            uploadManager.put(localFilePath, fileName, upToken);
        } catch (QiniuException ex) {
            ex.printStackTrace();
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    /**
     * ͨ���������ϴ�����ţ�ƿռ�
     * @param inputStream Ҫ�ϴ����ļ�
     * @param fileName    �ļ���
     */
    public static void uploadByInputStream(InputStream inputStream, String fileName) {
        try {
            uploadManager.put(inputStream, fileName, upToken, null, null);
            System.out.println("�ϴ��ɹ�");
        } catch (QiniuException ex) {
            System.err.println("�ϴ�ʧ��");
            ex.printStackTrace();
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                // ignore
            }
        }
    }

    /**
     * ͨ���ֽ������ϴ�
     *
     * @param file     Ҫ�ϴ����ļ�
     * @param fileName �ļ���
     */
    public static void uploadByByteArray(byte[] file, String fileName) {
        try {
            uploadManager.put(file, fileName, upToken);
            System.out.println("�ϴ��ɹ�");
        } catch (QiniuException ex) {
            System.err.println("�ϴ�ʧ��");
            ex.printStackTrace();
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    /**
     * �����ļ���ɾ��bucket�е��ļ�
     * @param fileName �ļ���
     */
    public static void delFile(String fileName) {
        try {
            BucketManager bucketManager = new BucketManager(auth, cfg);
            bucketManager.delete(BUCKET, fileName);
            System.out.println("ɾ���ɹ�");
        } catch (QiniuException ex) {
            //��������쳣��˵��ɾ��ʧ��
            System.out.println("ɾ��ʧ��");
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }

}
