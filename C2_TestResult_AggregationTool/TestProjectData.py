from collections import OrderedDict


class TestProjectData:
    def __init__(self):
        try:
            self.obj_vif_data=OrderedDict()
            self.moi_run_rep = OrderedDict()
            self.csv_files_path = OrderedDict()
        except Exception as e:
            print("TestProjectData _ __init__ :-" + str(e))
