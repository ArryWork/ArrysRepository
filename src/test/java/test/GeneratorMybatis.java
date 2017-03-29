package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * 逆向工程
 *
 * @author 陈志斌
 */
public class GeneratorMybatis {
	public void generator() throws Exception{
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		// 指定逆向工程配置文件
		File configFile = new File("src/test/java/generatorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}
	
	public static void main(String[] args){
		try {
			GeneratorMybatis generator = new GeneratorMybatis();
			generator.generator();
			System.out.println("成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
