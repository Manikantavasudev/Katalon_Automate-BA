from collections import OrderedDict


class CompareResults:
    @staticmethod
    def Compare(obj_json_data, test_result, test_vif, ref_result, ref_vif):
        try:
            for proj_name in test_result.keys():
                for moi_name in test_result[proj_name].keys():
                    for run in test_vif[proj_name].moi_run_rep[moi_name].keys():
                        test_vif_values = OrderedDict()
                        ref_vif_values = OrderedDict()
                        test_vif_values["Vendor"] = test_vif[proj_name].obj_vif_data[run]["Vendor"]
                        ref_vif_values["Vendor"] = None
                        test_vif_values["Dev Type"] = test_vif[proj_name].obj_vif_data[run]["Dev Type"]
                        ref_vif_values["Dev Type"] = None
                        additional_vif_field = obj_json_data.moi_specific_vif_data[moi_name]
                        if len(additional_vif_field):
                            for field in additional_vif_field:
                                if field not in test_vif_values:
                                    test_vif_values[field] = test_vif[proj_name].obj_vif_data[run][field]
                                if field not in ref_vif_values:
                                    ref_vif_values[field] = None
                        ref_moi = None
                        ref_run = None
                        ref_found = False
                        for ref_proj_name in ref_vif.keys():
                            if not ref_found:
                                for ref_run in ref_vif[ref_proj_name].obj_vif_data.keys():
                                    if not ref_found:
                                        for field in ref_vif_values:
                                            ref_vif_values[field] = ref_vif[ref_proj_name].obj_vif_data[ref_run][field]
                                        if ref_vif_values == test_vif_values:
                                            if moi_name in ref_result[ref_proj_name]:
                                                if ref_run in ref_vif[ref_proj_name].moi_run_rep[moi_name]:
                                                    ref_moi = ref_result[ref_proj_name][moi_name]
                                                    ref_found = True
                                                    break
                        if ref_found:
                            for test_id in test_result[proj_name][moi_name].keys():
                                test_run_data = test_result[proj_name][moi_name][test_id].run_data[run]
                                test_run_data.ref_result = ref_moi[test_id].run_data[ref_run].test_last_run_result
                                test_run_data.ref_file =ref_proj_name
                                test_run_data.ref_run = ref_run
                                if test_run_data.ref_result == test_run_data.test_last_run_result:
                                    test_run_data.compare_result = "TRUE"
                                else:
                                    test_run_data.compare_result = "FALSE"
                                test_result[proj_name][moi_name][test_id].run_data[run] = test_run_data

            return test_result
        except Exception as e:
            print("CompareResults _ process_files :-" + str(e))
