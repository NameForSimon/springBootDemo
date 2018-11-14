package com.wndz.application;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wndz.bean.Wndz;
import com.wndz.mapper.WndzMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;


@Api(value = "Helloworld测试", tags = { "测试接口" })
@Controller
public class HelloController {
	@Autowired
	private WndzMapper wndzMapper;
	
	@RequestMapping("/helloworld")
	public String hello() {
		//Controller不能写返回的文字了，因为配置文件下配置了返回templates目录下的html文件，RestController才能写返回的文字
		return "admin";
	}
	@RequestMapping("/helloworld2")
	public String hello2() {
		Logger log = Logger.getLogger(this.getClass());
		log.info("hello2成功");
		return "select";
	}
	@RequestMapping("/helloworld3")
	public String hello3(Map<String,String> map) {
		map.put("msg", "百度搜索");
		return "wndz";
	}
	
	@RequestMapping("/testjson")
	@ApiOperation("获取用户信息")
	@ApiImplicitParam(name = "name", value = "用户名", dataType = "string", paramType = "query")
	@CrossOrigin(origins="http://localhost:8088")//使用 Sublime Text 的插件启动html测试，用controller跳转不行，没有8088端口，只能直接启动html读取测试，加了这解释可以在8088端口读取这个html，一般前端ajax使用，后端无关
	@ResponseBody
	public Wndz test() {
		Wndz wndz = new Wndz();
		wndz.wid=1;
		wndz.name="百度搜索";
		wndz.url="www.baidu.com";
		wndz.date=new Date();
		return wndz;
	}
	
	@RequestMapping("/helloworld4")
	public String hello4() {
		return "upload";
	}
	
	@RequestMapping("/helloworld5")
	public String hello5() {
		return "corstest";
	}
	
	@RequestMapping("/helloworld6")
	public String hello6() {
		return "webSockTest";
	}
	
	@RequestMapping("/helloworld7")
	public String hello7() {
		Wndz wndz = new Wndz();
		wndz.wid=2;
		wndz.name="百度搜索";
		wndz.url="www.baidu.com";
		wndz.date=new Date();
		wndzMapper.insert(wndz);
		return "success";
	}
	
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		 if (!file.isEmpty()) {
	            String saveFileName = file.getOriginalFilename();
	            File saveFile = new File(request.getSession().getServletContext().getRealPath("/upload/") + saveFileName);
	            System.out.println("保存路径为："+saveFile);
	            if (!saveFile.getParentFile().exists()) {
	                saveFile.getParentFile().mkdirs();
	            }
	            try {
	                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
	                out.write(file.getBytes());
	                out.flush();
	                out.close();
	                return "success";
	            } catch (FileNotFoundException e) {
	                e.printStackTrace();
	                return "上传失败";
	            } catch (IOException e) {
	                e.printStackTrace();
	                return "上传失败";
	            }
	        } else {
	            return "上传失败";
	        }
	}
	
	@RequestMapping("/download")
	public String download(HttpServletRequest request, HttpServletResponse response) {
	String fileName = "background.JPG";// 设置文件名，根据业务需要替换成要下载的文件名
    if (fileName != null) {
        //设置文件路径
        String realPath = "D:\\工作\\";
        File file = new File(realPath , fileName);
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("success");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    return null;
}
	
	
	@ApiOperation("获取用户信息")
	@ApiImplicitParam(name = "name", value = "用户名", dataType = "String", paramType = "query")
	@GetMapping("/pub/{name}")
	@ResponseBody
	public Wndz pub(@PathVariable("name") String name ) {
		Wndz wndz = new Wndz();
		wndz.name=name;
		return wndz;
	}
	
	
}
