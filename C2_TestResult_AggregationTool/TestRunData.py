from collections import OrderedDict


class TestRunData:
    def __init__(self):
        try:
            self.test_last_run_result = "NS"
            self.ref_result = "NS"
            self.compare_result = "NS"
            self.ref_file=""
            self.ref_run = ""
            self.rep_data = OrderedDict()
        except Exception as e:
            print("TestRunData _ __init__ :-" + str(e))
