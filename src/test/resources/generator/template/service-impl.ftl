package ${basePackage}.service.${subPackage}.impl;

import ${basePackage}.dao.${subPackage}.${modelNameUpperCamel}Dao;
import ${basePackage}.model.${subPackage}.${modelNameUpperCamel};
import ${basePackage}.service.${subPackage}.${modelNameUpperCamel}Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ${author} on ${date}.
 */
@Service
@Transactional
public class ${modelNameUpperCamel}ServiceImpl implements ${modelNameUpperCamel}Service {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ${modelNameUpperCamel}Dao ${modelNameLowerCamel}Dao;

    /**
     * 新增
     */
	@Override
	public int save(${modelNameUpperCamel} ${modelNameLowerCamel}){
        return ${modelNameLowerCamel}Dao.insertSelective(${modelNameLowerCamel});
    }

    /**
     * 删除
     */
    @Override
    public int deleteById(Integer id){
        return ${modelNameLowerCamel}Dao.deleteByPrimaryKey(id.longValue());
    }

    /**
     * 更新
     */
    @Override
    public int update(${modelNameUpperCamel} ${modelNameLowerCamel}){
        return ${modelNameLowerCamel}Dao.updateByPrimaryKey(${modelNameLowerCamel});
    }

    /**
     * 详情
     */
    @Override
    public ${modelNameUpperCamel} findById(Integer id){
        return ${modelNameLowerCamel}Dao.selectByPrimaryKey(id.longValue());
    }

    /**
     * 列表
     */
    @Override
    public List<${modelNameUpperCamel}> findAll(){
        return null;
    }

}
