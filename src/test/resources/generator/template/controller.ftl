package ${basePackage}.controller.${subPackage};
import ${basePackage}.common.base.BaseResult;
import ${basePackage}.model.${subPackage}.${modelNameUpperCamel};
import ${basePackage}.service.${subPackage}.${modelNameUpperCamel}Service;
import ${basePackage}.common.utils.LocalBindingErrorUtil;
import ${basePackage}.common.base.BasePage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import java.util.List;

/**
* Created by ${author} on ${date}.
*/
@RestController
@RequestMapping("${baseRequestMapping}")
@Api(tags = "")
public class ${modelNameUpperCamel}Controller {

    @Autowired
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    /**
     * 新增
     * @param ${modelNameLowerCamel}
     * @param bindingResult
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增")
    @ApiImplicitParam(name = "${modelNameLowerCamel}", value = "新增信息", required = true, dataType = "${modelNameUpperCamel}")
    public BaseResult<String> add(@Valid @RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
          return LocalBindingErrorUtil.handle(bindingResult,String.class);
        }
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
         return new BaseResult<String>();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "int",paramType = "path")
    public BaseResult<String> delete(@PathVariable Integer id) {
        ${modelNameLowerCamel}Service.deleteById(id);
        return new BaseResult<String>();
    }

    /**
     * 更新
     * @param ${modelNameLowerCamel}
     * @param bindingResult
     * @return
     */
    @PutMapping("/update")
    @ApiOperation(value = "更新")
    @ApiImplicitParam(name = "${modelNameLowerCamel}", value = "更新信息", required = true, dataType = "${modelNameUpperCamel}")
    public BaseResult<String> update(@Valid @RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
          return LocalBindingErrorUtil.handle(bindingResult,String.class);
        }
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
         return new BaseResult<String>();
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    @ApiOperation(value = "详情")
    @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "int",paramType = "path")
    public BaseResult<${modelNameUpperCamel}> detail(@PathVariable Integer id) {
        return new BaseResult<${modelNameUpperCamel}>(${modelNameLowerCamel}Service.findById(id));
    }

    /**
     * 分页
     * @param page
     * @return
     */
    @PostMapping("/page")
    @ApiOperation(value = "分页")
    @ApiImplicitParam(name = "page", value = "分页信息", required = true, dataType = "BasePage")
    public BaseResult<${modelNameUpperCamel}> page(@RequestBody BasePage page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findAll();
        PageInfo<${modelNameUpperCamel}> pageInfo = new PageInfo<>(list);
        return new BaseResult<${modelNameUpperCamel}>(pageInfo);
    }
}
