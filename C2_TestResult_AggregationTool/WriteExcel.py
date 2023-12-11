import os
from collections import OrderedDict
from add_mismatch import *

import xlsxwriter


class WriteExcel:

    def __init__(self, obj_json_data, test_result_data, csv_proj_files_data):

        try:
            self.moi_proj_name_data = OrderedDict()
            self.obj_json_data = obj_json_data
            self.test_result_data = test_result_data
            self.updated_result_data = self.test_result_data
            self.csv_proj_files_data = csv_proj_files_data
            self.summary_result = OrderedDict()
            self.test_id_summary=OrderedDict()
            self.display_result_type = ['TOTAL', 'PASS', 'FAIL', 'INCOMPLETE', 'NOT_EXECUTED', 'NA', 'OTHERS', 'NS']
            self.heading_all_moi = {'MOI Name': 20, 'Test No': 7, 'Test ID': 10, 'Test Name': 30, 'Result': 10,
                                    'Ref Result': 10,
                                    'Compare': 10}
            self.heading_result = {'MOI Name': 30, 'Total': 10, 'Pass': 10, 'Fail': 10, 'Incomp': 10,
                                   'Not Exe': 10, 'Not App': 10, 'Others': 10, 'Not Sel': 10}
            self.heading_vif = {'Date': 10, 'Five Port': 7, 'C2 Sno': 15, 'Test Er': 10, 'Port Type': 15,
                                'VIF Name': 15, 'VIF Spec': 10, 'PD Spec Rev': 10, 'Folder Name': 40,
                                "Folder Abs Path": 25,
                                'Vendor': 10, 'SW Ver': 10, 'FW Ver': 10, 'Dev Type': 10}
            self.display_heading = {'Folder Name': 40, 'MOI Name': 30, 'Run': 10, 'Total': 10, 'Pass': 10, 'Fail': 10,
                                    'Incomp': 10, 'Not Exe': 10, 'Not App': 10, 'Others': 10, 'Not Sel': 10}
            self.workbook_proj = xlsxwriter.Workbook
            self.workbook = xlsxwriter.Workbook(self.obj_json_data.report_file_name)
            self.load_workbook_format(self.workbook)
            self.workbook_row = xlsxwriter.Workbook(self.obj_json_data.rowwise_file_name)
            self.workbook_analysis = xlsxwriter.Workbook(self.obj_json_data.analysis_file_name)
        except Exception as e:
            print("WriteExcel _ __init__ :-" + str(e))

    def select_format(self, result):
        try:
            if result == "PASS":
                return self.pass_format
            elif result == "FAIL":
                return self.fail_format
            elif result == "INCOMPLETE":
                return self.incomplete_format
            elif result == "NOT_EXECUTED":
                return self.not_executed_format
            elif result == "NA":
                return self.na_format
            elif result == "OTHERS":
                return self.others_format
            elif result == "NS":
                return self.ns_format
            elif result == "TRUE":
                return self.true_format
            elif result == "FALSE":
                return self.false_format
        except Exception as e:
            print("WriteExcel _ select_format :-" + str(e))

    def load_workbook_format(self, workbook):
        try:

            self.bold = workbook.add_format({'bold': True, 'border': 1})
            self.odd_format = workbook.add_format(
                {'bg_color': '#F0E6E5', 'border': 1})
            self.head_format = workbook.add_format(
                {'bg_color': '#702D2F', 'border': 1, 'font_color': '#FFFFFF', 'align': 'center',
                 'valign': 'vcenter', 'border_color': '#FFFFFF'})
            self.even_format = workbook.add_format(
                {'bg_color': '#fdf8f6', 'border': 1})
            self.pass_format = workbook.add_format(
                {'bg_color': '#09ff00', 'border': 1})
            self.fail_format = workbook.add_format(
                {'bg_color': '#ff4e3d', 'border': 1})
            self.true_format = workbook.add_format(
                {'bg_color': '#09ff00', 'border': 1})
            self.false_format = workbook.add_format(
                {'bg_color': '#ff4e3d', 'border': 1})
            self.incomplete_format = workbook.add_format(
                {'bg_color': '#ffff4d', 'border': 1})
            self.not_executed_format = workbook.add_format(
                {'bg_color': '#ff6600', 'border': 1})
            self.na_format = workbook.add_format(
                {'bg_color': 'silver', 'border': 1})
            self.others_format = workbook.add_format(
                {'bg_color': 'pink', 'border': 1})
            self.summary_head_format = workbook.add_format(
                {'bg_color': '#702D2F', 'border': 1, 'font_color': '#FFFFFF', 'font_size': '20', 'align': 'center'})
            self.ns_format = workbook.add_format(
                {'bg_color': '#F0CB90', 'border': 1})
            self.merge_format = workbook.add_format({
                'bold': 1,
                'border': 1,
                'align': 'center',
                'valign': 'vcenter',
                'fg_color': 'yellow'})
        except Exception as e:
            print("WriteExcel _ load_workbook_format :-" + str(e))

    def data_write(self):
        try:

            self.create_all_moi_tc_result()
            self.create_proj_moi_sheet()
            self.create_proj_moi_sheet(True)
            self.create_display_all_tc_worksheet()
            self.analysis_result()
            self.workbook_obj = load_workbook(self.new_file_name)

            #to add mismatch count in excel
            add_mismatch(self.new_file_name)
        except Exception as e:
            print("WriteExcel _ data_write :-" + str(e))

    def analysis_result(self):
        try:

            a=1
            #self.test_id_summary[ 'DETERMINISTIC_TESTS' ]['TD.PD.SRC.E2'].count('NA'and'NS')

        except Exception as e:
            print("WriteExcel _ analysis_result :-" + str(e))

    def create_all_moi_tc_result(self):
        try:

            self.summary_all_test()
            self.summary_all_moi_write()
            self.all_tc_result_write()
            self.workbook.close()

        except Exception as e:
            print("WriteExcel _ create_all_moi_tc_result :-" + str(e))

    def summary_all_test(self):
        try:
            for proj_name in self.updated_result_data.keys():
                if proj_name not in self.summary_result:
                    self.summary_result[proj_name] = OrderedDict()
                for moi_name in self.updated_result_data[proj_name].keys():
                    if moi_name not in self.summary_result[proj_name]:
                        self.summary_result[proj_name][moi_name] = OrderedDict()
                    if moi_name not in self.test_id_summary:
                        self.test_id_summary[moi_name] = OrderedDict()
                    for test_id in self.updated_result_data[proj_name][moi_name].keys():
                        if test_id not in self.test_id_summary[moi_name]:
                            self.test_id_summary[moi_name][test_id]=[]
                        test_id_result = self.updated_result_data[proj_name][moi_name][test_id]
                        for run in test_id_result.run_data.keys():
                            if run not in self.summary_result[proj_name][moi_name]:
                                self.summary_result[proj_name][moi_name][run] = OrderedDict()
                                for item in self.display_result_type:
                                    self.summary_result[proj_name][moi_name][run][item] = 0
                            result = test_id_result.run_data[run].test_last_run_result
                            self.test_id_summary[moi_name][test_id].append(result)
                            if result == "SKIPPED" or result == "WARNING" or result == "RUNNING" or result == "RESERVED" \
                                    or result == "ABORTED":
                                result = "OTHERS"
                            self.summary_result[proj_name][moi_name][run][result] += 1
                            self.summary_result[proj_name][moi_name][run]['TOTAL'] += 1
        except Exception as e:
            print("WriteExcel _ summary_all_test :-" + str(e))

    def summary_all_moi_write(self):
        try:
            if 'Summary' not in self.workbook.sheetnames:
                sum_worksheet = self.workbook.add_worksheet('Summary')
                col_index = 0
                col_index_head_start = col_index
                row_index = 0
                head_no = 0
                for head_no, heading in enumerate(self.obj_json_data.vif_display_heading):
                    sum_worksheet.write(row_index, col_index + head_no, heading, self.head_format)
                    sum_worksheet.set_column(col_index + head_no, col_index + head_no,
                                             self.heading_vif[heading])
                col_index += head_no + 1
                for head_no, heading in enumerate(self.heading_result.keys()):
                    sum_worksheet.write(row_index, col_index + head_no, heading, self.head_format)
                    sum_worksheet.set_column(col_index + head_no, col_index + head_no,
                                             self.heading_result[heading])
                sum_worksheet.autofilter(row_index, col_index_head_start, row_index, col_index + head_no)
                for proj_no, proj_name in enumerate(self.summary_result.keys()):
                    format_row = self.even_format if proj_no % 2 == 0 else self.odd_format
                    for moi_name in self.summary_result[proj_name].keys():
                        for run in self.summary_result[proj_name][moi_name].keys():
                            row_index += 1
                            col_index = 0
                            for vif_field in self.obj_json_data.vif_display_heading:
                                sum_worksheet.write(row_index, col_index,
                                                    self.csv_proj_files_data[proj_name].obj_vif_data[run][vif_field],
                                                    format_row)
                                col_index += 1
                            sum_worksheet.write(row_index, col_index, moi_name, format_row)
                            col_index += 1
                            for type_no, result_type in enumerate(self.summary_result[proj_name][moi_name][run].keys()):
                                sum_worksheet.write_number(row_index, col_index + type_no,
                                                           self.summary_result[proj_name][moi_name][run][result_type],
                                                           format_row)
        except Exception as e:
            print("WriteExcel _ summary_worksheet_write :-" + str(e))

    def all_tc_result_write(self):
        try:
            if 'All_MOI_TC_Reults' not in self.workbook.sheetnames:
                moi_tc_worksheet = self.workbook.add_worksheet('All_MOI_TC_Reults')
                col_index = 0
                col_index_head_start = col_index
                row_index = 0
                head_no = 0
                for head_no, heading in enumerate(self.obj_json_data.vif_display_heading):
                    moi_tc_worksheet.write(row_index, col_index + head_no, heading, self.head_format)
                    moi_tc_worksheet.set_column(col_index + head_no, col_index + head_no,
                                                self.heading_vif[heading])
                col_index += head_no + 1
                for head_no, heading in enumerate(self.heading_all_moi.keys()):
                    moi_tc_worksheet.write(row_index, col_index + head_no, heading, self.head_format)
                    moi_tc_worksheet.set_column(col_index + head_no, col_index + head_no,
                                                self.heading_all_moi[heading])
                moi_tc_worksheet.autofilter(row_index, col_index_head_start, row_index, col_index + head_no)
                row_index += 1
                test_no = 0
                for proj_no, proj_name in enumerate(self.updated_result_data.keys()):
                    for moi_name in self.updated_result_data[proj_name].keys():
                        for test_id in self.updated_result_data[proj_name][moi_name].keys():
                            test_id_result = self.updated_result_data[proj_name][moi_name][test_id]
                            for run in test_id_result.run_data.keys():
                                format_row = self.even_format if row_index % 2 == 0 else self.odd_format
                                test_no += 1
                                col_index = 0
                                for vif_field in self.obj_json_data.vif_display_heading:
                                    moi_tc_worksheet.write(row_index, col_index,
                                                           self.csv_proj_files_data[proj_name].obj_vif_data[run][
                                                               vif_field], format_row)
                                    col_index += 1
                                moi_tc_worksheet.write(row_index, col_index, moi_name, format_row)
                                col_index += 1
                                moi_tc_worksheet.write(row_index, col_index, test_no, format_row)
                                col_index += 1
                                moi_tc_worksheet.write(row_index, col_index, test_id_result.test_id, format_row)
                                col_index += 1
                                moi_tc_worksheet.write(row_index, col_index, test_id_result.test_name, format_row)
                                col_index += 1
                                run_result = test_id_result.run_data[run]
                                moi_tc_worksheet.write(row_index, col_index, run_result.test_last_run_result,
                                                       self.select_format(run_result.test_last_run_result))
                                col_index += 1
                                moi_tc_worksheet.write(row_index, col_index, run_result.ref_result,
                                                       self.select_format(run_result.ref_result))
                                col_index += 1
                                moi_tc_worksheet.write(row_index, col_index, run_result.compare_result,
                                                       self.select_format(run_result.compare_result))
                                row_index += 1
        except Exception as e:
            print("WriteExcel _ create_all_moi_tc_result :-" + str(e))

    def create_proj_moi_sheet(self, is_ref_result=False):
        try:
            for proj_name in self.updated_result_data.keys():
                self.create_new_workbook(proj_name, is_ref_result)
                self.summary_worksheet_write(proj_name, self.summary_result[proj_name])
                self.create_moi_sheet(self.updated_result_data[proj_name],
                                      self.csv_proj_files_data[proj_name], is_ref_result)
                self.workbook_proj.close()
        except Exception as e:
            print("WriteExcel _ create_proj_moi_sheet :-" + str(e))

    def create_new_workbook(self, filename, is_ref_result):
        try:
            if is_ref_result:
                filename += "_With_Ref"
            path = os.path.dirname(self.obj_json_data.report_file_name)
            add_folder = os.path.splitext(os.path.basename(self.obj_json_data.report_file_name))[0]
            if is_ref_result:
                add_folder += "_With_Ref"
            new_folder_path = os.path.join(path, add_folder)
            if not os.path.exists(new_folder_path):
                os.mkdir(new_folder_path)
            file_name_with_type = filename + ".xlsx"
            new_file = os.path.join(new_folder_path, file_name_with_type)
            self.workbook_proj = xlsxwriter.Workbook(new_file)
            self.new_file_name = new_file
            self.load_workbook_format(self.workbook_proj)
        except Exception as e:
            print("WriteExcel _ create_new_workbook :-" + str(e))

    def summary_worksheet_write(self, proj_name, summary_result):
        try:
            if 'Summary' not in self.workbook_proj.sheetnames:
                # sum_worksheet = self.workbook_new.add_worksheet("Summary")
                sum_worksheet = self.workbook_proj.add_worksheet('Summary')
                row_index = 0
                col_index = 0
                format_row = self.odd_format
                sum_worksheet.merge_range(row_index, col_index, row_index, col_index + 10, 'Test Execution Info',
                                          self.summary_head_format)
                row_index += 1
                head_no = 0
                run_vif = list(self.csv_proj_files_data[proj_name].obj_vif_data.keys())[0]
                for head_no, heading in enumerate(self.obj_json_data.vif_display_heading):
                    sum_worksheet.write(row_index + head_no, col_index, heading, format_row)
                    sum_worksheet.merge_range(row_index + head_no, col_index + 1, row_index + head_no, col_index + 10,
                                              self.csv_proj_files_data[proj_name].obj_vif_data[run_vif][heading],
                                              format_row)
                row_index += head_no + 1
                for head_no, heading in enumerate(self.display_heading.keys()):
                    sum_worksheet.write(row_index, col_index + head_no, heading, self.head_format)
                    sum_worksheet.set_column(col_index + head_no, col_index + head_no,
                                             self.display_heading[heading])

                for moi_name in summary_result.keys():
                    for run in summary_result[moi_name].keys():
                        row_index += 1
                        sum_worksheet.write(row_index, col_index, proj_name, self.odd_format)
                        sum_worksheet.write(row_index, col_index + 1, moi_name, self.odd_format)
                        sum_worksheet.write(row_index, col_index + 2, run.upper(), self.odd_format)
                        for type_no, result_type in enumerate(summary_result[moi_name][run].keys()):
                            sum_worksheet.write_number(row_index, col_index + type_no + 3,
                                                       summary_result[moi_name][run][result_type], self.odd_format)

        except Exception as e:
            print("WriteExcel _ summary_worksheet_write :-" + str(e))

    def create_moi_sheet(self, updated_result_data, csv_proj_files_data, is_ref_result):
        try:
            moi_run_rep_data = csv_proj_files_data.moi_run_rep
            col_index = 0
            for moi_name in updated_result_data.keys():
                if moi_name not in self.workbook_proj.sheetnames:
                    moi_worksheet = self.workbook_proj.add_worksheet(moi_name)
                    for head_no, col_width in enumerate([5, 15, 60, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14]):
                        moi_worksheet.set_column(col_index + head_no, col_index + head_no, col_width)
                    row_index_start = 1
                    additional_vif_field = self.obj_json_data.moi_specific_vif_data[moi_name]
                    col_index = 2
                    row_no = 0
                    temp_row_index = row_index_start
                    if len(additional_vif_field) or is_ref_result:
                        moi_worksheet.merge_range(row_index_start - 1, col_index - 2, row_index_start - 1, col_index,
                                                  "Details", self.head_format)
                    if len(additional_vif_field):
                        temp_row_index += 1
                        for row_no, field in enumerate(additional_vif_field):
                            row_no += temp_row_index
                            format_row = self.even_format if row_no % 2 == 0 else self.odd_format
                            moi_worksheet.merge_range(row_index_start, col_index - 2, row_index_start, col_index, field,
                                                      format_row)
                            row_index_start += 1
                            row_no += 1
                    if is_ref_result:
                        format_row = self.even_format if row_no % 2 == 0 else self.odd_format
                        moi_worksheet.merge_range(row_index_start, col_index - 2, row_index_start, col_index,
                                                  "Ref File Name", format_row)
                        row_no += 1
                        row_index_start += 1
                        format_row = self.even_format if row_no % 2 == 0 else self.odd_format
                        moi_worksheet.merge_range(row_index_start, col_index - 2, row_index_start, col_index,
                                                  "Ref Run No", format_row)

                    merge_st_col_index = col_index + 1

                    for run_no, run in enumerate(moi_run_rep_data[moi_name]):
                        row_no = 1
                        row_index_start = 1
                        for test_no, test_id in enumerate(updated_result_data[moi_name].keys()):
                            merge_end_col_index = merge_st_col_index + len(moi_run_rep_data[moi_name][run])
                            if is_ref_result:
                                merge_end_col_index = merge_st_col_index + len(moi_run_rep_data[moi_name][run]) + 2
                            if len(additional_vif_field) or is_ref_result:
                                moi_worksheet.merge_range(row_index_start - 1, merge_st_col_index, row_index_start - 1,
                                                          merge_end_col_index,
                                                          run.upper(),
                                                          self.head_format)

                            row_no += 1
                            field_no = 0
                            if len(additional_vif_field):
                                for field_no, field in enumerate(additional_vif_field):
                                    field_no += row_no
                                    format_row = self.even_format if field_no % 2 == 0 else self.odd_format
                                    moi_worksheet.merge_range(row_index_start, merge_st_col_index, row_index_start,
                                                              merge_end_col_index,
                                                              csv_proj_files_data.obj_vif_data[run][field], format_row)
                                    row_index_start += 1
                                    field_no -= 1
                            if is_ref_result:
                                row_no = field_no
                                format_row = self.even_format if row_no % 2 == 0 else self.odd_format
                                moi_worksheet.merge_range(row_index_start, merge_st_col_index, row_index_start,
                                                          merge_end_col_index,
                                                          updated_result_data[moi_name][test_id].run_data[run].ref_file,
                                                          format_row)
                                row_index_start += 1
                                row_no += 1
                                format_row = self.even_format if row_no % 2 == 0 else self.odd_format
                                moi_worksheet.merge_range(row_index_start, merge_st_col_index, row_index_start,
                                                          merge_end_col_index,
                                                          updated_result_data[moi_name][test_id].run_data[run].ref_run,
                                                          format_row)
                                row_index_start += 1
                            col_index += 1
                            merge_st_col_index = merge_end_col_index + 1
                            break

                    col_index = 0
                    row_index = row_index_start + 4
                    rep_no = 0
                    for test_no, test_id in enumerate(updated_result_data[moi_name].keys()):
                        if test_no % 2 == 0:
                            format_row = self.even_format
                        else:
                            format_row = self.odd_format
                        moi_worksheet.write(row_index, col_index, test_no + 1, format_row)
                        moi_worksheet.write(row_index, col_index + 1,
                                            updated_result_data[moi_name][test_id].test_id,
                                            format_row)
                        moi_worksheet.write(row_index, col_index + 2,
                                            updated_result_data[moi_name][test_id].test_name, format_row)
                        consolidated_no = 0
                        column_count = 0
                        for run_no, run in enumerate(moi_run_rep_data[moi_name]):
                            if run in updated_result_data[moi_name][test_id].run_data.keys():
                                for rep_no, rep in enumerate(moi_run_rep_data[moi_name][run]):
                                    column_count = run_no + rep_no + consolidated_no
                                    if rep in updated_result_data[moi_name][test_id].run_data[run].rep_data.keys():
                                        moi_worksheet.write(row_index, col_index + 3 + column_count,
                                                            updated_result_data[moi_name][test_id].run_data[
                                                                run].rep_data[rep],
                                                            self.select_format(
                                                                updated_result_data[moi_name][test_id].run_data[
                                                                    run].rep_data[rep]))
                                moi_worksheet.write(row_index, col_index + 4 + column_count,
                                                    updated_result_data[moi_name][test_id].run_data[
                                                        run].test_last_run_result, self.select_format(
                                        updated_result_data[moi_name][test_id].run_data[run].test_last_run_result))
                                consolidated_no += 1
                                if is_ref_result:
                                    moi_worksheet.write(row_index, col_index + 5 + column_count,
                                                        updated_result_data[moi_name][test_id].run_data[
                                                            run].ref_result, self.select_format(
                                            updated_result_data[moi_name][test_id].run_data[run].ref_result))
                                    consolidated_no += 1
                                    moi_worksheet.write(row_index, col_index + 6 + column_count,
                                                        updated_result_data[moi_name][test_id].run_data[
                                                            run].compare_result, self.select_format(
                                            updated_result_data[moi_name][test_id].run_data[run].compare_result))
                                    consolidated_no += 1
                            else:
                                moi_worksheet.write(row_index, col_index + 3 + run_no + rep_no, "NS",
                                                    self.select_format("NS"))
                                moi_worksheet.write(row_index, col_index + 4 + run_no + rep_no,
                                                    updated_result_data[moi_name][test_id].run_data[
                                                        run].test_last_run_result,
                                                    self.select_format(
                                                        updated_result_data[moi_name][test_id].run_data[
                                                            run].test_last_run_result))
                        row_index += 1
                    else:
                        row_index += 1
                    moi_worksheet.merge_range(row_index_start, col_index, row_index_start + 2, col_index, 'S.No',
                                              self.head_format)
                    moi_worksheet.merge_range(row_index_start, col_index + 1, row_index_start + 2, col_index + 1,
                                              'Test ID', self.head_format)
                    moi_worksheet.merge_range(row_index_start, col_index + 2, row_index_start + 2, col_index + 2,
                                              'Test Name', self.head_format)
                    run_no = 0
                    rep_no = 0
                    consolidated_no = 0
                    column_count = 0
                    for run_no, run in enumerate(moi_run_rep_data[moi_name]):
                        moi_worksheet.write(row_index_start + 1, col_index + 3 + run_no + consolidated_no, run.upper(),
                                            self.head_format)
                        run_column_start = col_index + 3 + run_no + consolidated_no
                        column_count = run_no + rep_no + consolidated_no
                        for rep_no, rep in enumerate(moi_run_rep_data[moi_name][run]):
                            column_count = run_no + rep_no + consolidated_no
                            moi_worksheet.write(row_index_start + 2, col_index + 3 + column_count, rep.upper(),
                                                self.head_format)
                        moi_worksheet.write(row_index_start + 2, col_index + 4 + column_count,
                                            "Consolidated", self.head_format)
                        moi_worksheet.merge_range(row_index_start + 1, run_column_start, row_index_start + 1,
                                                  col_index + 4 + column_count,
                                                  run.upper(), self.head_format)
                        consolidated_no += 1
                        if is_ref_result:
                            moi_worksheet.merge_range(row_index_start + 1, col_index + 5 + column_count,
                                                      row_index_start + 2, col_index + 5 + column_count,
                                                      "Ref Result", self.head_format)
                            moi_worksheet.merge_range(row_index_start + 1, col_index + 6 + column_count,
                                                      row_index_start + 2, col_index + 6 + column_count,
                                                      "Comp_Result", self.head_format)
                            consolidated_no += 2
                        column_count = run_no + rep_no + consolidated_no

                    moi_worksheet.merge_range(row_index_start, col_index + 3, row_index_start,
                                              col_index + 4 + column_count - 1,
                                              'Test Result', self.head_format)
                    for i in range(col_index + 4 + run_no + rep_no + 1 + consolidated_no - 1):
                        moi_worksheet.write(row_index_start + 3, col_index + i, '', self.head_format)
                    moi_worksheet.autofilter(row_index_start + 3, col_index, row_index,
                                             col_index + 4 + column_count - 1)
        except Exception as e:
            print("WriteExcel _ create_moi_sheet :-" + str(e))

    def create_display_all_tc_worksheet(self):
        try:
            # To change Dict vaules into MOI,Project_name from Project_name,MOI
            for proj_name in self.updated_result_data.keys():
                for moi_name in self.updated_result_data[proj_name].keys():
                    if moi_name not in self.moi_proj_name_data:
                        self.moi_proj_name_data[moi_name] = []
                    self.moi_proj_name_data[moi_name].append(proj_name)
            self.load_workbook_format(self.workbook_row)
            for moi_name in self.moi_proj_name_data.keys():
                additional_vif_field = self.obj_json_data.moi_specific_vif_data[moi_name]
                if moi_name not in self.workbook_row.sheetnames:
                    row_moi_worksheet = self.workbook_row.add_worksheet(moi_name)
                    row_index = 0
                    col_index = 0
                    head_no = 0
                    # To set Column Width
                    for head_no, col_width in enumerate([5, 15, 60]):
                        row_moi_worksheet.set_column(col_index + head_no, col_index + head_no, col_width)
                    # Writing VIF Heading
                    for head_no, heading in enumerate(self.obj_json_data.row_vif_display_heading):
                        format_row = self.even_format if head_no % 2 == 0 else self.odd_format
                        row_moi_worksheet.merge_range(row_index + head_no, col_index, row_index + head_no,
                                                      col_index + 2, heading, format_row)
                    row_index += 1 + head_no
                    # Writing Additional VIF Heading
                    vif_head_count = head_no
                    if len(additional_vif_field):
                        for head_no, heading in enumerate(additional_vif_field):
                            format_row = self.even_format if (head_no + vif_head_count) % 2 == 0 else self.odd_format
                            row_moi_worksheet.merge_range(row_index + head_no, col_index, row_index + head_no,
                                                          col_index + 2, heading, format_row)
                        row_index += 1 + head_no
                    # writing Heading  S.No,TestId,TestName
                    row_moi_worksheet.write(row_index, col_index, "S.No", self.head_format)
                    row_moi_worksheet.write(row_index, col_index + 1, "TestID", self.head_format)
                    row_moi_worksheet.write(row_index, col_index + 2, "Test Name", self.head_format)
                    row_index += 1
                    # writing All testCase Details(S.No,TestId,TestName)
                    for test_no, test_id in enumerate(self.obj_json_data.pd_test_cases_dict[moi_name]):
                        format_row = self.even_format if test_no % 2 == 0 else self.odd_format
                        row_moi_worksheet.write(row_index + test_no, col_index, test_no + 1, format_row)
                        row_moi_worksheet.write(row_index + test_no, col_index + 1, test_id, format_row)
                        row_moi_worksheet.write(row_index + test_no, col_index + 2,
                                                self.obj_json_data.pd_test_cases_dict[moi_name][test_id], format_row)
                    col_index += 3
                    # To write projectwise test result
                    for proj_name in self.moi_proj_name_data[moi_name]:
                        for run in self.csv_proj_files_data[proj_name].moi_run_rep[moi_name]:
                            row_index = 0
                            head_no = 0
                            row_moi_worksheet.set_column(col_index, col_index, 11)
                            # To write vif details
                            for head_no, heading in enumerate(self.obj_json_data.row_vif_display_heading):
                                format_row = self.even_format if head_no % 2 == 0 else self.odd_format
                                row_moi_worksheet.write(row_index + head_no, col_index,
                                                        self.csv_proj_files_data[proj_name].obj_vif_data[run][heading],
                                                        format_row)
                            row_index += 1 + head_no
                            # To write Additional vif details
                            vif_head_count = head_no
                            if len(additional_vif_field):
                                for head_no, heading in enumerate(additional_vif_field):
                                    format_row = self.even_format if (head_no + vif_head_count) % 2 == 0 else self.odd_format
                                    run_details = self.csv_proj_files_data[proj_name].obj_vif_data[run]
                                    if heading == "VCONN_voltage":
                                        if run_details["Dev Type"] == "Cable":
                                            row_moi_worksheet.write(row_index + head_no, col_index,
                                                                    run_details[heading], format_row)
                                        else:
                                            row_moi_worksheet.write(row_index + head_no, col_index, " ", format_row)

                                    else:
                                        row_moi_worksheet.write(row_index + head_no, col_index,
                                                                run_details[heading],format_row)
                                row_index += 1 + head_no
                            row_moi_worksheet.write(row_index, col_index, "Result", self.head_format)
                            row_index += 1
                            # To write test results
                            test_data=self.updated_result_data[proj_name][moi_name]
                            for test_no, test_id in enumerate(test_data.keys()):
                                row_moi_worksheet.write(row_index + test_no, col_index,
                                                        test_data[test_id].run_data[run].test_last_run_result,
                                                        self.select_format(test_data[test_id].run_data[run].test_last_run_result))
                            col_index += 1
                    row_moi_worksheet.autofilter(
                        len(self.obj_json_data.row_vif_display_heading) + len(additional_vif_field), 0,
                        len(self.obj_json_data.row_vif_display_heading) + len(additional_vif_field), col_index - 1)
                    row_moi_worksheet.freeze_panes(
                        len(self.obj_json_data.row_vif_display_heading) + len(additional_vif_field) + 1, 3)

            self.workbook_row.close()
        except Exception as e:
            print("WriteExcel _ create_display_all_tc_worksheet :-" + str(e))
