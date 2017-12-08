package ${basePackage}.service.${subPackage};
import ${basePackage}.model.${subPackage}.${modelNameUpperCamel};
import java.util.List;


/**
 * Created by ${author} on ${date}.
 */
public interface ${modelNameUpperCamel}Service{

    /**
     * 新增
     */
    public int save(${modelNameUpperCamel} ${modelNameLowerCamel});

    /**
     * 删除
     */
    public int deleteById(Integer id);

    /**
     * 更新
     */
    public int update(${modelNameUpperCamel} ${modelNameLowerCamel});

    /**
     * 详情
     */
    public ${modelNameUpperCamel} findById(Integer id);

    /**
     * 列表
     */
    public List<${modelNameUpperCamel}> findAll();

}
